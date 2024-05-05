package com.juny.core.ch05.service;

import com.juny.core.ch05.model.ApplicationUser;
import com.juny.core.ch05.model.CustomUser;
import com.juny.core.ch05.repository.ApplicationUserRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

  private final UserService userService;

  private final LoginAttemptService loginAttemptService;

  public CustomUserDetailsService(UserService userService, LoginAttemptService loginAttemptService) {
    this.userService = userService;
    this.loginAttemptService = loginAttemptService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    if (loginAttemptService.isBlocked(username)){
      throw new LockedException("user account is locked.");
    }
    ApplicationUser user = userService.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("No user with " + username + " exists in the system");
    }

    SimpleGrantedAuthority simpleGrantedAuthority = null;
    if (user.isTotpEnabled()){
      simpleGrantedAuthority = new SimpleGrantedAuthority(
          "TOTP_AUTH_AUTHORITY");
    } else {
      simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
    }
    System.out.println("applicationUser.toString() = " + user.toString());
    CustomUser customUser = new CustomUser(
        user.getUsername(),
        user.getPassword(),
        user.isVerified(),
        true,
        true,
        true,
        Arrays.asList(simpleGrantedAuthority));
    customUser.setTotpEnabled(user.isTotpEnabled());
    return customUser;
//    return User.withUsername(username).password(user.getPassword()).roles("USER").disabled(!user.isVerified()).build();
  }
}
