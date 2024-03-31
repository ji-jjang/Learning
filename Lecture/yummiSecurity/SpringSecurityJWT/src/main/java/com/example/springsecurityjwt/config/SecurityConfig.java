package com.example.springsecurityjwt.config;

import com.example.springsecurityjwt.jwt.JWTFilter;
import com.example.springsecurityjwt.jwt.JWTUtil;
import com.example.springsecurityjwt.jwt.LoginFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final AuthenticationConfiguration authenticationConfiguration;
  private final JWTUtil jwtUtil;

  public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {
    this.authenticationConfiguration = authenticationConfiguration;
    this.jwtUtil = jwtUtil;
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {

    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        .cors((cors) -> cors
            .configurationSource(new CorsConfigurationSource() {
              @Override
              public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                CorsConfiguration configuration = new CorsConfiguration();

                configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                configuration.setAllowedMethods(Collections.singletonList("*"));
                configuration.setAllowCredentials(true);
                configuration.setAllowedHeaders(Collections.singletonList("*"));
                configuration.setMaxAge(3600L);

                configuration.setExposedHeaders(Collections.singletonList("Authorization"));

                return null;
              }
            })
        );
    //csrf disable
    http
        .csrf(AbstractHttpConfigurer::disable);

    //From 로그인 방식 disable
    http
        .formLogin(AbstractHttpConfigurer::disable);

    //http basic 인증 방식 disable
    http
        .httpBasic(AbstractHttpConfigurer::disable);

    http
        .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);
    http
        .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);

    //경로별 인가 작업
    http
        .authorizeHttpRequests((auth) -> auth
            .requestMatchers("/login", "/", "/join").permitAll()
            .requestMatchers("/admin").hasRole("ADMIN")
            .anyRequest().authenticated());

    //세션 설정
    http
        .sessionManagement((session) -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

    return configuration.getAuthenticationManager();
  }
}
