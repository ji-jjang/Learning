package com.juny.core.ch05.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;

@Service
public class LoginAttemptService {

  private static final int MAX_ATTEMPTS = 3;

  private LoadingCache<String, Integer> loginAttemptCache;

  public LoginAttemptService() {
    loginAttemptCache = CacheBuilder.newBuilder()
        .expireAfterWrite(1, TimeUnit.DAYS)
        .build(
            new CacheLoader<String, Integer>() {
              @Override
              public Integer load(final String key) {
                return 0;
              }
            });
  }

  public void loginSuccess(final String username) {
    loginAttemptCache.invalidate(username);
  }

  public void loginFailed(String username){
    int failedAttemptCount = 0;

    try {
      failedAttemptCount = loginAttemptCache.get(username);
    } catch (ExecutionException e){
      failedAttemptCount = 0;
    }

    failedAttemptCount++;
    loginAttemptCache.put(username, failedAttemptCount);
  }

  public boolean isBlocked(String username) {
    try {
      return loginAttemptCache.get(username) >= MAX_ATTEMPTS;
    } catch (ExecutionException e){
      return false;
    }
  }
}
