package com.example.springjwt.controller;

import com.example.springjwt.entity.RefreshEntity;
import com.example.springjwt.jwt.JWTUtil;
import com.example.springjwt.repository.RefreshRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.Date;

@RestController
public class ReissueController {

  private final JWTUtil jwtUtil;

  private final RefreshRepository refreshRepository;

  public ReissueController(JWTUtil jwtUtil, RefreshRepository refreshRepository) {
    this.jwtUtil = jwtUtil;
    this.refreshRepository = refreshRepository;
  }

  @PostMapping("/reissue")
  public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {

    String refresh = null;
    Cookie[] cookies = request.getCookies();
    for (var e : cookies) {
      if (e.getName().equals("refresh")) {
        refresh = e.getValue();
      }
    }
    System.out.println("refresh = " + refresh);

    if (refresh == null) {

      return new ResponseEntity<>("refresh token null", HttpStatus.BAD_REQUEST);
    }

    try {
      jwtUtil.isExpired(refresh);
    } catch (ExpiredJwtException e) {

      return new ResponseEntity<>("refresh token expired", HttpStatus.BAD_REQUEST);
    }

    // 필요할ㄲ? refresh랑 겹치는 쿠키 부분 가져온건데?
    String type = jwtUtil.getType(refresh);
    System.out.println("type = " + type);
    if (!type.equals("refresh")) {

      return new ResponseEntity<>("invalid refresh token!!", HttpStatus.BAD_REQUEST);
    }

    Boolean isExist = refreshRepository.existsByRefresh(refresh);
    if (!isExist) {

      return new ResponseEntity<>("invalid refresh token!", HttpStatus.BAD_REQUEST);
    }

    String username = jwtUtil.getUsername(refresh);
    String role = jwtUtil.getRole(refresh);

    String newAccess = jwtUtil.createJwt("access", username, role, 600_000L);
    String newRefresh = jwtUtil.createJwt("refresh", username, role, 86_400_000L);

    refreshRepository.deleteByRefresh(refresh);
    addRefreshEntity(username, newRefresh, 86_400_000L);


    response.setHeader("access", newAccess);
    response.addCookie(createCookie("refresh", newRefresh));

    return new ResponseEntity<>(HttpStatus.OK);
  }

  private void addRefreshEntity(String username, String refresh, long expiredMs) {
    Date date = new Date(System.currentTimeMillis() + expiredMs);

    RefreshEntity refreshEntity = new RefreshEntity();
    refreshEntity.setUsername(username);
    refreshEntity.setRefresh(refresh);
    refreshEntity.setExpiration(date.toString());

    refreshRepository.save(refreshEntity);
  }

  private Cookie createCookie(String key, String value) {
    Cookie cookie = new Cookie(key, value);
    cookie.setMaxAge(24 * 60 * 60);
    //cookie.setSecure(true);
    //cookie.setPath("/");
    cookie.setHttpOnly(true);
    return cookie;
  }
}


// refresh=eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInVzZXJuYW1lIjoiYWRtaW4iLCJyb2xlIjoiUk9MRV9BRE1JTiIsImlhdCI6MTcxMTk0NTI4MCwiZXhwIjoxNzEyMDMxNjgwfQ.d2PtYVR6LLnR698Z1PGGAaMpAjVhvu3_4qPJ7q7uo6A; Max-Age=86400; Expires=Tue, 02 Apr 2024 04:21:20 GMT; HttpOnly
// refresh=eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInVzZXJuYW1lIjoiYWRtaW4iLCJyb2xlIjoiUk9MRV9BRE1JTiIsImlhdCI6MTcxMTk0NTMwNSwiZXhwIjoxNzEyMDMxNzA1fQ.w1uOs5TXPD8gHB72Xed7NSQGuqSdGqPFn5mG-crIlnE; Max-Age=86400; Expires=Tue, 02 Apr 2024 04:21:45 GMT; HttpOnly
