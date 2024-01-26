package com.epam.springboot.crudrest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

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

  // Platzi course
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            customizeRequests -> {
              customizeRequests
                  .requestMatchers(HttpMethod.POST)
                  .hasAuthority("create_expense")
                  .requestMatchers(HttpMethod.PATCH)
                  .hasRole("ADMIN")
                  .anyRequest()
                  .authenticated();
            })
        .logout(l -> l.logoutUrl("/logout"))
        .cors(Customizer.withDefaults())
        .csrf(AbstractHttpConfigurer::disable)
        .httpBasic(Customizer.withDefaults());

    // To allow h2-console
    http.headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
    return http.build();
  }

  // When we create a service that implements UserDetailsService and override loadUserByUsername we
  // can remove this code.
  //  @Bean
  //  public UserDetailsService memoryUser() {
  //    UserDetails admin =
  //        User.builder()
  //            .username("admin")
  //            .password(passwordEncoder().encode("admin"))
  //            .roles("ADMIN")
  //            .build();
  //
  //    UserDetails customer =
  //        User.builder()
  //            .username("customer")
  //            .password(passwordEncoder().encode("customer123"))
  //            .roles("CUSTOMER")
  //            .build();
  //
  //    return new InMemoryUserDetailsManager(admin, customer);
  //  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
