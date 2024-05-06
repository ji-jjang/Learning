package com.juny.core.ch05.listener;

import com.juny.core.ch05.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationFailureEventListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

  private final LoginAttemptService loginAttemptService;

  public AuthenticationFailureEventListener(LoginAttemptService loginAttemptService) {
    this.loginAttemptService = loginAttemptService;
  }

  @Override
  public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
    System.out.println("AuthenticationFailureEventListener.onApplicationEvent");
    String username = (String) event.getAuthentication().getPrincipal();
    loginAttemptService.loginFailed(username);
  }

  @Override
  public boolean supportsAsyncExecution() {
    return ApplicationListener.super.supportsAsyncExecution();
  }
}
