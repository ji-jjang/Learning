package com.juny.core.ch05.handler;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class DefaultAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    if (isTotpAuthRequired(authentication)){
      redirectStrategy.sendRedirect(request, response, "/totp-login");
    } else{
      redirectStrategy.sendRedirect(request, response, "/account");
    }

  }

  private boolean isTotpAuthRequired(Authentication authentication) {
    Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
    return authentication.getAuthorities().contains("TOTP_AUTH_AUTHORITY");
  }

}
