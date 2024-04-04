package com.juny.securityjwtoauth2.service;

import com.juny.securityjwtoauth2.dto.*;
import com.juny.securityjwtoauth2.entity.UserEntity;
import com.juny.securityjwtoauth2.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  private final UserRepository userRepository;

  public CustomOAuth2UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

    OAuth2User oAuth2User = super.loadUser(userRequest);

    System.out.println(oAuth2User);

    String registrationId = userRequest.getClientRegistration().getRegistrationId();
    OAuth2Response oAuth2Response = null;
    log.info("oAuthUser.getAttributes: {}", oAuth2User.getAttributes());
    if (registrationId.equals("naver")) {

      oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
    }
    else if (registrationId.equals("google")) {

      oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
    }
    else {

      return null;
    }
    // username은? email에 저장하는 게 나을까? email이 아니라 userId에 저장하는 게 좋을듯? 식별자니까.
    //
    String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
    UserEntity existData = userRepository.findByUsername(username);

    if (existData == null) {

      UserEntity userEntity = new UserEntity();
      userEntity.setUsername(username);
      userEntity.setEmail(oAuth2Response.getEmail());
      userEntity.setName(oAuth2Response.getName());
      userEntity.setRole("ROLE_USER");

      userRepository.save(userEntity);

      UserDTO userDTO = new UserDTO();
      userDTO.setUsername(username);
      userDTO.setName(oAuth2Response.getName());
      userDTO.setRole("ROLE_USER");

      return new CustomOAuth2User(userDTO);
    }
    else {

      existData.setEmail(oAuth2Response.getEmail());
      existData.setName(oAuth2Response.getName());

      userRepository.save(existData);

      UserDTO userDTO = new UserDTO();
      userDTO.setUsername(existData.getUsername());
      userDTO.setName(oAuth2Response.getName());
      userDTO.setRole(existData.getRole());

      return new CustomOAuth2User(userDTO);
    }
  }
}
