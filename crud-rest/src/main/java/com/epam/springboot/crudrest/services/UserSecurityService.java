package com.epam.springboot.crudrest.services;

import com.epam.springboot.crudrest.models.UserEntity;
import com.epam.springboot.crudrest.models.UserRoleEntity;
import com.epam.springboot.crudrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSecurityService implements UserDetailsService {
  @Autowired private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity userEntity =
        this.userRepository
            .findById(username)
            .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found."));

    var roles = userEntity.getRoles().stream().map(UserRoleEntity::getRole).toArray(String[]::new);

    return User.builder()
        .username(userEntity.getUsername())
        .password(userEntity.getPassword())
        .authorities(this.grantedAuthorities(roles))
        .accountLocked(userEntity.getLocked())
        .disabled(userEntity.getDisabled())
        .build();
  }

  private String[] getAuthorities(String role) {
    if ("ADMIN".equals(role) || "CUSTOMER".equals(role)) {
      return new String[] {"create_expense"};
    }

    return new String[] {};
  }

  private List<GrantedAuthority> grantedAuthorities(String[] roles) {
    List<GrantedAuthority> authorities = new ArrayList<>(roles.length);

    for (String role : roles) {
      authorities.add(new SimpleGrantedAuthority("ROLE_" + role));

      for (String authority : this.getAuthorities(role)) {
        authorities.add(new SimpleGrantedAuthority(authority));
      }
    }

    return authorities;
  }
}
