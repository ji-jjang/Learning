package com.practice.react.security;

import com.practice.react.model.UserEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
@Slf4j
@Service
public class TokenProvider {
  private SecretKey SECRET_KEY;

  @PostConstruct
  public void init() {
    String base64Key = "juny2juny2juny2juny2juny2juny2juny2juny2juny2juny2juny2juny2juny2juny2juny2juny2juny2juny2";
    byte[] decodedKey = Base64.getDecoder().decode(base64Key);
    SECRET_KEY = Keys.hmacShaKeyFor(decodedKey);
  }

  public String create(UserEntity userEntity) {
    Date expiryDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

    String token = Jwts.builder()
        .setSubject(userEntity.getId())
        .setIssuer("demo app")
        .setIssuedAt(new Date())
        .setExpiration(expiryDate)
        .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
        .compact();
    System.out.println("TokenProvider.create");
    System.out.println("token = " + token);
    return token;
  }

  public String validateAndGetUserId(String token) {
    System.out.println("TokenProvider.validateAndGetUserId");
    System.out.println("token = " + token);
    Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();

    System.out.println("claims.getSubject() = " + claims.getSubject());
    return claims.getSubject();
  }
}