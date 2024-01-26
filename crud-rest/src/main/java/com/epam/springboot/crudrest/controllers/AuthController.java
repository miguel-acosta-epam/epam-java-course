package com.epam.springboot.crudrest.controllers;

import com.epam.springboot.crudrest.config.JwtUtil;
import com.epam.springboot.crudrest.models.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired private AuthenticationManager authenticationManager;
  @Autowired private JwtUtil jwtUtil;

  @PostMapping("/login")
  public ResponseEntity<Void> login(@RequestBody LoginDTO loginDTO) {

    UsernamePasswordAuthenticationToken login =
        new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

    Authentication authentication = this.authenticationManager.authenticate(login);

    System.out.println(authentication.isAuthenticated());
    System.out.println(authentication.getPrincipal());

    String jwt = this.jwtUtil.create(loginDTO.getUsername());

    return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
  }
}
