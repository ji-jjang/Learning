package com.juny.core.ch05.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
  private DefaultRedirectStrategy defaultRedirectStrategy = new DefaultRedirectStrategy();

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

    log.info("login failure, onAuthenticationFailure: {}", exception.getMessage());
    if(exception instanceof DisabledException) {
      defaultRedirectStrategy.sendRedirect(request, response, "/login-disabled");
      return;
    }

    if(exception.getCause() instanceof LockedException){
      defaultRedirectStrategy.sendRedirect(request, response, "/login-locked");
      return ;
    }
    defaultRedirectStrategy.sendRedirect(request, response, "/login-error");
  }

}
