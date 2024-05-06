package com.juny.core.ch05.service;

import com.juny.core.ch05.model.ApplicationUser;
import com.juny.core.ch05.model.CustomUser;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  private final UserService userService;
  private final LoginAttemptService loginAttemptService;

  public CustomOAuth2UserService(UserService userService, LoginAttemptService loginAttemptService) {
    this.userService = userService;
    this.loginAttemptService = loginAttemptService;
  }

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    System.out.println("CustomOAuth2UserService.loadUser");
    DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
    OAuth2User oAuth2User = delegate.loadUser(userRequest);

    String username = oAuth2User.getName();
    if (loginAttemptService.isBlocked(username)) {
      throw new LockedException("User account is locked.");
    }

    ApplicationUser applicationUser = userService.findByUsername(username);
    if (applicationUser == null) {
      applicationUser = registerNewUser(oAuth2User);
    }

    return createCustomUser(applicationUser, oAuth2User.getAttributes());
  }

  private ApplicationUser registerNewUser(OAuth2User oAuth2User) {
    ApplicationUser newUser = new ApplicationUser();
    newUser.setUsername(oAuth2User.getAttribute("email"));
    newUser.setVerified(true);
    newUser.setEmail(oAuth2User.getAttribute("email"));

    userService.save(newUser);
    return newUser;
  }

  private OAuth2User createCustomUser(ApplicationUser user, Map<String, Object> attributes) {
    List<GrantedAuthority> authorities = Collections.singletonList(
        new SimpleGrantedAuthority("ROLE_USER"));
//    List<GrantedAuthority> authorities = Collections.singletonList(
//        new SimpleGrantedAuthority(user.isTotpEnabled() ? "TOTP_AUTH_AUTHORITY" : "ROLE_USER"));

    CustomUser customUser = new CustomUser(
        user.getUsername(),
        "N/A", // 소셜 로그인에서는 비밀번호가 적용되지 않음
        user.isVerified(),
        true,
        true,
        true,
        authorities);
    System.out.println("customUser = " + customUser);
//    customUser.setTotpEnabled(user.isTotpEnabled());

    return new DefaultOAuth2User(authorities, attributes, "email");
  }
}
