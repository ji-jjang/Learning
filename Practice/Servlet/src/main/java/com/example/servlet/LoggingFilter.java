package com.example.servlet;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;
import org.springframework.core.annotation.Order;

@WebFilter("/*")
@Order(1)
public class LoggingFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
    FilterChain filterChain) throws IOException, ServletException {

    System.out.println("Start LogginFilter");
    filterChain.doFilter(servletRequest, servletResponse);
    System.out.println("End LogginFilter");
  }
}
