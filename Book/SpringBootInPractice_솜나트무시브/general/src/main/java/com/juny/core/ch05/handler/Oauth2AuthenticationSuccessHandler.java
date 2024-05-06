package com.juny.core.ch05.handler;

import com.juny.core.ch05.model.ApplicationUser;
import com.juny.core.ch05.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class Oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

    OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
    System.out.println("Oauth2AuthenticationSuccessHandler.onAuthenticationSuccess");

    redirectStrategy.sendRedirect(request, response, "/index");
  }
}
