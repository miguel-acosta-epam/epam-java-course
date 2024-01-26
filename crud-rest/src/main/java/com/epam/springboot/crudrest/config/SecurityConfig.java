package com.epam.springboot.crudrest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Autowired private JwtFilter jwtFilter;

  // Platzi course
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors(Customizer.withDefaults())
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(
            sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(
            customizeRequests -> {
              customizeRequests
                  .requestMatchers("/api/auth/**")
                  .permitAll()
                  //                  .requestMatchers(HttpMethod.POST)
                  //                  .hasAuthority("create_expense")
                  .requestMatchers(HttpMethod.PATCH)
                  .hasRole("ADMIN")
                  .anyRequest()
                  .authenticated();
            })
        .logout(l -> l.logoutUrl("/logout"))
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    // To allow h2-console
    http.headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
      throws Exception {
    return configuration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // Main Spring boot Course
  // For oauth2 unccoment pom.xml oauthclient dependency
  //  @Bean
  //  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  //    return http.authorizeHttpRequests(
  //            a ->
  //                a.requestMatchers("/", "/error", "/webjars/**")
  //                    .permitAll()
  //                    .anyRequest()
  //                    .authenticated())
  //        .logout(LogoutConfigurer::permitAll)
  //        .oauth2Login(Customizer.withDefaults())
  //        .formLogin(Customizer.withDefaults())
  //        .build();
  //  }
  //
  //  @Autowired
  //  public void ConfigureGlobal(AuthenticationManagerBuilder auth) throws Exception {
  //    auth.inMemoryAuthentication()
  //        .passwordEncoder(NoOpPasswordEncoder.getInstance())
  //        .withUser("user")
  //        .password("password")
  //        .roles("USER");
  //  }
}
