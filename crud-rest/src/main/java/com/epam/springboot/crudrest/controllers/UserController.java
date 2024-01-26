package com.epam.springboot.crudrest.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

  @GetMapping
  public Map<String, Object> user() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    return Collections.singletonMap("name", authentication.getName());
  }
}
