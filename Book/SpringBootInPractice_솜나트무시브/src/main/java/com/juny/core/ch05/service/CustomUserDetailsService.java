package com.juny.core.ch05.service;

import com.juny.core.ch05.model.ApplicationUser;
import com.juny.core.ch05.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

  private final UserService userService;

  public CustomUserDetailsService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    ApplicationUser applicationUser = userService.findByUsername(username);
    if (applicationUser == null) {
      throw new UsernameNotFoundException("No user with "+username+" exists in the system");
    }
    System.out.println("applicationUser.toString() = " + applicationUser.toString());
    return User.withUsername(username).password(applicationUser.getPassword()).roles("USER").disabled(false).build();
//    return User.builder()
//        .username(applicationUser.getUsername())
//        .password(applicationUser.getPassword())
//        .disabled(!applicationUser.isVerified())
//        .accountExpired(applicationUser.isAccountCredentialsExpired())
//        .accountLocked(applicationUser.isLocked())
//        //.roles("USER")
//        .authorities("ROLE_USER")
//        .build();
  }
}
