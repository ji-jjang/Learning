package juny.securitysessionoauth.config;

import juny.securitysessionoauth.oauth2.CustomClientRegistrationRepository;
import juny.securitysessionoauth.oauth2.CustomOAuth2AuthorizedClientService;
import juny.securitysessionoauth.service.CustomOauth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final CustomOauth2UserService customOAuth2UserService;

  private final CustomClientRegistrationRepository customClientRegistrationRepository;

  private final CustomOAuth2AuthorizedClientService customOAuth2AuthorizedClientService;

  private final JdbcTemplate jdbcTemplate;

  public SecurityConfig(CustomOauth2UserService customOAuth2UserService,
                        CustomClientRegistrationRepository clientRegistrationRepository,
                        CustomOAuth2AuthorizedClientService customOAuth2AuthorizedClientService, JdbcTemplate jdbcTemplate) {

    this.customOAuth2UserService = customOAuth2UserService;
    this.customClientRegistrationRepository = clientRegistrationRepository;
    this.customOAuth2AuthorizedClientService = customOAuth2AuthorizedClientService;
    this.jdbcTemplate = jdbcTemplate;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        .csrf((csrf) -> csrf.disable());

    http
        .formLogin((login) -> login.disable());

    http
        .httpBasic((basic) -> basic.disable());

    http
        .oauth2Login(Customizer.withDefaults());

//    http
//        .oauth2Login((oauth2) -> oauth2
//            .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
//                .userService(customOAuth2UserService)));

//    http
//        .oauth2Login((oauth2) -> oauth2
//            .loginPage("/login")
//            .userInfoEndpoint((userInfoEndpointConfig) ->
//                userInfoEndpointConfig.userService(customOAuth2UserService)));

    http
        .oauth2Login((oauth2) -> oauth2
            .loginPage("/login")
            .clientRegistrationRepository(customClientRegistrationRepository.clientRegistrationRepository())
            .authorizedClientService(
                customOAuth2AuthorizedClientService.oAuth2AuthorizedClientService(
                    jdbcTemplate,
                    customClientRegistrationRepository.clientRegistrationRepository()))
            .userInfoEndpoint((userInfoEndpointConfig) ->
                userInfoEndpointConfig.userService(customOAuth2UserService)));

    http
        .authorizeHttpRequests((auth) -> auth
            .requestMatchers("/", "/oauth2/**", "/login/**").permitAll()
            .anyRequest().authenticated());

    return http.build();
  }
}
