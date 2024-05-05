package com.juny.core.ch05.listener;

import com.juny.core.ch05.model.ApplicationUser;
import com.juny.core.ch05.service.LoginAttemptService;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
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
    ApplicationUser user = (ApplicationUser) event.getAuthentication().getPrincipal();
    loginAttemptService.loginSuccess(user.getUsername());
  }
}
