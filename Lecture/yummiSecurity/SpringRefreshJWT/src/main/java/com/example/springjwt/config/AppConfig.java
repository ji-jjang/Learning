package com.example.springjwt.config;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
public class AppConfig {
  @Autowired
  @Qualifier("springSecurityFilterChain")
  private Filter springSecurityFilterChain;

  @PostConstruct
  public void printSecurityFilters() {
    FilterChainProxy filterChainProxy = (FilterChainProxy) springSecurityFilterChain;
    List<SecurityFilterChain> list = filterChainProxy.getFilterChains();
    list.stream()
        .flatMap(chain -> chain.getFilters().stream())
        .forEach(filter -> System.out.println(filter.getClass().getName()));
  }
}
