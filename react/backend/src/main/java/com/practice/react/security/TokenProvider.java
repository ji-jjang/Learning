package com.practice.react.security;

import com.practice.react.model.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
public class TokenProvider {
  private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

  public String create(UserEntity userEntity) {
    Date expiryDate = Date.from(
        Instant.now()
            .plus(1, ChronoUnit.DAYS));
    return Jwts.builder()
        .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
        .setSubject(userEntity.getId())
        .setIssuer("demo app")
        .setIssuedAt(new Date())
        .setExpiration(expiryDate)
        .compact();
  }

  public String validateAndGetUserId(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .build()
        .parseClaimsJws(token)
        .getBody();

    return claims.getSubject();
  }
}
