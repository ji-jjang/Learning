package com.juny.core.ch05.security;

import com.juny.core.ch05.filter.TotpAuthFilter;
import com.juny.core.ch05.handler.CustomAuthenticationFailureHandler;
import com.juny.core.ch05.handler.DefaultAuthenticationSuccessHandler;
import com.juny.core.ch05.handler.Oauth2AuthenticationSuccessHandler;
import com.juny.core.ch05.repository.ApplicationUserRepository;
import com.juny.core.ch05.service.CustomOAuth2UserService;
import com.juny.core.ch05.service.CustomUserDetailsService;
import com.juny.core.ch05.service.LoginAttemptService;
import com.juny.core.ch05.service.UserService;
import com.juny.core.ch07.JwtConverter;
import javax.sql.DataSource; import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.ldap.core.support.BaseLdapPathContextSource;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.ldap.EmbeddedLdapServerContextSourceFactoryBean;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.ldap.userdetails.PersonContextMapper;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

  private final AccessDeniedHandler customAccessDeniedHandler;
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
  private final LoginAttemptService loginAttemptService;
  private final CustomOAuth2UserService customOauth2UserService;
  private final JwtConverter jwtConverter;


  public SecurityConfig(CustomAccessDeniedHandler customAccessDeniedHandler, DataSource dataSource,
      UserService userService, PasswordEncoder passwordEncoder,
      CustomAuthenticationFailureHandler customAuthenticationFailureHandler,
      LoginAttemptService loginAttemptService, CustomOAuth2UserService customOauth2UserService,
      JwtConverter jwtConverter) {
    this.customAccessDeniedHandler = customAccessDeniedHandler;
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
    this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
    this.loginAttemptService = loginAttemptService;
    this.customOauth2UserService = customOauth2UserService;
    this.jwtConverter = jwtConverter;
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return new CustomUserDetailsService(userService, loginAttemptService);
  }
//  @Bean
//  public DefaultSpringSecurityContextSource contextSource() {
//    DefaultSpringSecurityContextSource contextSource =
//        new DefaultSpringSecurityContextSource("ldap://localhost:8389/dc=manning,dc=com");
//    return contextSource;
//  }
//
//  @Bean
//  public LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder> ldapAuthenticationConfigurer(
//      DefaultSpringSecurityContextSource contextSource) {
//    LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder> configurer =
//        new LdapAuthenticationProviderConfigurer<>();
//    configurer
//        .userDnPatterns("uid={0},ou=people")
//        .contextSource(contextSource)
//        .passwordCompare()
//        .passwordEncoder(NoOpPasswordEncoder.getInstance())
//        .passwordAttribute("userPassword");
//    return configurer;
//  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) ->
        web.ignoring().requestMatchers("/webjars/**", "/images/**", "/css/**", "/h2-console/**");
  }
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http, TotpAuthFilter totpAuthFilter) throws Exception {

    http.addFilterBefore(totpAuthFilter, UsernamePasswordAuthenticationFilter.class);

    http
        .csrf(c -> c.ignoringRequestMatchers(PathRequest.toH2Console()))
            .headers(h -> h.frameOptions(FrameOptionsConfig::sameOrigin));

    http
        .authorizeHttpRequests((auth) -> {
            auth
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .requestMatchers("/courses/**").hasAuthority("SCOPE_course:read")
                .requestMatchers("adduser", "/login", "/login-error", "/login-verified", "/login-disabled", "/verify/email", "login-locked").permitAll()
                .requestMatchers("/totp-login", "/totp-login-error").hasAuthority("TOTP_AUTH_AUTHORITY")
                .requestMatchers("/delete/**").hasRole("ADMIN")
                .anyRequest().hasRole("USER");

        }
    );

    http.rememberMe(
            rm -> rm.key("remember-me-key").rememberMeCookieName("course-tracker-remember-me"))
        .formLogin(
            f ->
                f.loginPage("/login")
                    .failureHandler(customAuthenticationFailureHandler)
                    .successHandler(new DefaultAuthenticationSuccessHandler()));

    http.oauth2Login(
        (oauth2) ->
            oauth2
                .loginPage("/login")
                .userInfoEndpoint(
                    (userInfoEndpointConfig) ->
                        userInfoEndpointConfig.userService(customOauth2UserService))
                .successHandler(new Oauth2AuthenticationSuccessHandler()));

    http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter)));

    return http.build();
  }
}
