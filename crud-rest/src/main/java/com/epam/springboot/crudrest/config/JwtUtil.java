package com.epam.springboot.crudrest.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
  private static final String SECRET_KEY = "ep4m-crud";
  private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

  public String create(String username) {
    return JWT.create()
        .withSubject(username)
        .withIssuer("epam-crud-rest-api")
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
        .sign(ALGORITHM);
  }

  public boolean isValid(String jwt) {
    try {
      JWT.require(ALGORITHM).build().verify(jwt);
      return true;
    } catch (JWTVerificationException error) {
      return false;
    }
  }

  public String getUsername(String jwt) {
    return JWT.require(ALGORITHM).build().verify(jwt).getSubject();
  }
}
