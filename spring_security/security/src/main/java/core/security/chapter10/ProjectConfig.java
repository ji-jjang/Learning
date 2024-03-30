package core.security.chapter10;

import lombok.ToString;
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
public class ProjectConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.addFilterAfter(new CsrfTokenLogger(), CsrfFilter.class)
          .authorizeRequests()
          .anyRequest().permitAll();

      return http.build();
    }
//        http.csrf(c -> {
//                    c.csrfTokenRepository(customTokenRepository());
//                    c.ignoringRequestMatchers("/ciao");
//                }).authorizeRequests()
//                .anyRequest().permitAll();
//
//        return http.build();

//    @Bean
//    public CsrfTokenRepository customTokenRepository() {
//        return new CustomCsrfTokenRepository();
//    }
}
        /*
        방법1 ignoringRequestMatchers
         */
//        http.csrf(c -> {
//                    c.ignoringRequestMatchers("/ciao");
//                }).authorizeRequests()
//                .anyRequest()
//                .permitAll();
        /*
        방법2 MvcReuqestMather 함께 사용
         */
//        HandlerMappingIntrospector i = new HandlerMappingIntrospector();
//        MvcRequestMatcher r = new MvcRequestMatcher(i, "/ciao");
//        http.csrf(c -> {
//                    c.ignoringRequestMatchers(r);
//                }).authorizeRequests()
//                .anyRequest()
//                .permitAll();

        /*
        방법3 정규식
         */
//        String pattern = ".*(ciao).*";
//        String httpMethod = HttpMethod.POST.name();
//        RegexRequestMatcher r = new RegexRequestMatcher(pattern, httpMethod);
//        http.csrf(c -> {
//                    c.ignoringRequestMatchers(r);
//                }).authorizeRequests()
//                .anyRequest()
//                .permitAll();
//        return http.build();
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest()
//                .authenticated();
//
//        http.formLogin(a -> a.defaultSuccessUrl("/main", true));
//
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService uds() {
//        InMemoryUserDetailsManager uds = new InMemoryUserDetailsManager();
//
//        UserDetails u1 = User.withUsername("mary")
//                .password("1234")
//                .authorities("READ")
//                .build();
//
//        uds.createUser(u1);
//
//        return uds;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
