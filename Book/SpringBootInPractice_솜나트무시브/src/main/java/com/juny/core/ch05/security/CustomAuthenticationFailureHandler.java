//package com.juny.core.ch05.security;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.DefaultRedirectStrategy;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
//
//  private DefaultRedirectStrategy defaultRedirectStrategy = new DefaultRedirectStrategy();
//
//  @Override
//  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//      AuthenticationException exception) throws IOException, ServletException {
//    System.out.println("CustomAuthenticationFailureHandler.onAuthenticationFailure");
//    System.out.println("Authentication Exception: " + exception.getMessage()); // 로그에 예외 메시지 추가
//
//    if (exception instanceof DisabledException) {
//      defaultRedirectStrategy.sendRedirect(request, response, "/login-disabled");
//      return;
//    }
//    defaultRedirectStrategy.sendRedirect(request, response, "/login-error");
//  }
//}
