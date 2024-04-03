package juny.securitysessionoauth.service;

import juny.securitysessionoauth.dto.CustomOAuth2User;
import juny.securitysessionoauth.dto.GoogleResponse;
import juny.securitysessionoauth.dto.NaverResponse;
import juny.securitysessionoauth.dto.OAuth2Response;
import juny.securitysessionoauth.entity.UserEntity;
import juny.securitysessionoauth.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOauth2UserService extends DefaultOAuth2UserService {

  private final UserRepository userRepository;

  public CustomOauth2UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);
    System.out.println(oAuth2User.getAttributes());

    String registrationId = userRequest.getClientRegistration().getRegistrationId();
    OAuth2Response oAuth2Response = null;
    if (registrationId.equals("naver")) {

      oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
    }
    else if (registrationId.equals("google")) {

      oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
    }
    else {

      return null;
    }
    String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
    UserEntity existData = userRepository.findByUsername(username);

    String role = "ROLE_USER";

    if (existData == null) {

      UserEntity userEntity = new UserEntity();
      userEntity.setUsername(username);
      userEntity.setEmail(oAuth2Response.getEmail());
      userEntity.setRole(role);

      userRepository.save(userEntity);
    }
    else {

      existData.setUsername(username);
      existData.setEmail(oAuth2Response.getEmail());

      role = existData.getRole();

      userRepository.save(existData);
    }

    return new CustomOAuth2User(oAuth2Response, role);
  }
}
