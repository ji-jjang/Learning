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
@Order(2)
public class LoggingFilter2 implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
    FilterChain filterChain) throws IOException, ServletException {

    System.out.println("Start LogginFilter2");
    filterChain.doFilter(servletRequest, servletResponse);
    System.out.println("End LogginFilter2");
  }
}
