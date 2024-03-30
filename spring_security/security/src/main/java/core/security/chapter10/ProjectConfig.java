package core.security.chapter10;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@Slf4j
public class ProjectConfig {

  @Bean
  public CsrfTokenRepository cusmtomeTokenRepository() {
    return new CustomCsrfTokenRepository();
  }
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


    http.csrf(c -> {
      c.csrfTokenRepository(cusmtomeTokenRepository());
//      c.ignoringRequestMatchers("/ciao");
    });

    http.authorizeRequests()
        .anyRequest().permitAll();

//    http.authorizeRequests()
//        .anyRequest().authenticated();
//
//    http.formLogin((formLogin) -> formLogin
//        .defaultSuccessUrl("/main", true));

    return http.build();
  }

  @Bean
  public UserDetailsService uds() {
    InMemoryUserDetailsManager uds = new InMemoryUserDetailsManager();

    UserDetails u1 = User.withUsername("mary")
        .password("1234")
        .authorities("READ")
        .build();

    uds.createUser(u1);

    return uds;
  }
  @Bean
  public PasswordEncoder passwordEncoder() {
      return NoOpPasswordEncoder.getInstance();
  }
}