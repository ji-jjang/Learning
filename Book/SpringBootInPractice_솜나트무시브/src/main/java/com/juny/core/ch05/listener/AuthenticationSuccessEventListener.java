package com.juny.core.ch05.listener;

import com.juny.core.ch05.model.ApplicationUser;
import com.juny.core.ch05.service.LoginAttemptService;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

  private final LoginAttemptService loginAttemptService;

  public AuthenticationSuccessEventListener(LoginAttemptService loginAttemptService) {
    this.loginAttemptService = loginAttemptService;
  }

  @Override
  public void onApplicationEvent(AuthenticationSuccessEvent event) {

    System.out.println("AuthenticationSuccessEventListener.onApplicationEvent");
    Object principal = event.getAuthentication().getPrincipal();

    if (principal instanceof ApplicationUser) {
      ApplicationUser user = (ApplicationUser) principal;
      loginAttemptService.loginSuccess(user.getUsername());
    } else if (principal instanceof DefaultOAuth2User) {
      DefaultOAuth2User oauthUser = (DefaultOAuth2User) principal;
      // Extract username or equivalent field from OAuth2User details if needed
      String username = oauthUser.getName();  // You might need to adjust this based on how your user's data is structured
      loginAttemptService.loginSuccess(username);
    } else {
      throw new IllegalArgumentException("Unknown principal type: " + principal.getClass());
    }
  }
}
