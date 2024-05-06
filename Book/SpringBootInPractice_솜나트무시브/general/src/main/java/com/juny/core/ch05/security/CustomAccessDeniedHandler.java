package com.juny.core.ch05.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {
//    System.out.println("request.getContextPath() = " + request.getContextPath());
//    response.sendRedirect(request.getContextPath() + "accessDenied.html");
    request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.FORBIDDEN);
    request.getRequestDispatcher("/accessDenied").forward(request, response);
  }
}
