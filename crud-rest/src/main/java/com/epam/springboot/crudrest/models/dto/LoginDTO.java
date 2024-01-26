package com.epam.springboot.crudrest.models.dto;

import lombok.Data;

@Data
public class LoginDTO {
  private String username;
  private String password;
}
