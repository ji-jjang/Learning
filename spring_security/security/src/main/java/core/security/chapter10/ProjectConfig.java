package core.security.chapter10;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@Slf4j
public class ProjectConfig{

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    http.csrf(c -> {
//      CorsConfiguration source = request -> {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowedOrigins(List.of("example.com", "example.org"));
//        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
//        return config;
//      };
//      c.configure(source);
//    })
//    http.csrf(c -> c.disable());

    http.authorizeRequests()
        .anyRequest()
        .permitAll();

    return http.build();
  }
}
//public class ProjectConfig {
//
//  @Bean
//  public CsrfTokenRepository cusmtomeTokenRepository() {
//    return new CstomCsrfTokenRepository();
//  }
//  @Bean
//  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//
//    http.csrf(c -> {
//      c.csrfTokenRepository(cusmtomeTokenRepository());
////      c.ignoringRequestMatchers("/ciao");
//    });
//
//    http.authorizeRequests()
//        .anyRequest().permitAll();
//
////    http.authorizeRequests()
////        .anyRequest().authenticated();
////
////    http.formLogin((formLogin) -> formLogin
////        .defaultSuccessUrl("/main", true));
//
//    return http.build();
//  }
//
//  @Bean
//  public UserDetailsService uds() {
//    InMemoryUserDetailsManager uds = new InMemoryUserDetailsManager();
//
//    UserDetails u1 = User.withUsername("mary")
//        .password("1234")
//        .authorities("READ")
//        .build();
//
//    uds.createUser(u1);
//
//    return uds;
//  }
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//      return NoOpPasswordEncoder.getInstance();
//  }
//}