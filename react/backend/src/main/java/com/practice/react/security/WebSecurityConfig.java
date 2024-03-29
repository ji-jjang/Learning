package com.practice.react.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@EnableWebSecurity
@Slf4j
@Configuration
public class WebSecurityConfig {

  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    System.out.println("WebSecurityConfig.securityFilterChain");
    http.cors(Customizer.withDefaults()) // webMvcConfig에서 설정했으므로 기본 cors 설정한다.
        .csrf((csrf) -> csrf.disable())
//        .csrf(AbstractHttpConfigurer::disable)
        .httpBasic((httpBasic) -> httpBasic.disable())
        .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeRequests()
        .requestMatchers("/", "/auth/**").permitAll()
        .requestMatchers("/auth/signup").permitAll()
        .anyRequest().authenticated();

//    http.addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//    http.addFilterAfter(
//        jwtAuthenticationFilter, CorsFilter.class
//    );

    return http.build();
  }
}
