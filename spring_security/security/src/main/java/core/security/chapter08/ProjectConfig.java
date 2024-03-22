package core.security.chapter08;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
public class ProjectConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults())
                .authorizeRequests()
                .requestMatchers(new RegexRequestMatcher(".*/(us|uk|ca)+/(en|fr).*", null)).authenticated()
                .anyRequest().hasRole("premium");
//                .requestMatchers("/email/{email:.*(.+@.+\\.com)}").permitAll().anyRequest().denyAll();

//                .requestMatchers(HttpMethod.GET, "/a").authenticated()
//                .requestMatchers(HttpMethod.POST, "/a").permitAll()
//                .requestMatchers("/a/b/**").authenticated()
//                .requestMatchers("/product/{code:^[0-9]*$}").permitAll()
//                .anyRequest().authenticated();

//                .requestMatchers("/hello").hasRole("ADMIN")
//                .requestMatchers("/ciao").hasRole("MANAGER")
//                .anyRequest()
//                .authenticated();

//                .permitAll();
//                .denyAll();
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        UserDetails user1 = User.withUsername("john")
                .password("1234")
                .authorities("read")
                .build();

        UserDetails user2 = User.withUsername("jane")
                .password("1234")
                .roles("read", "premium")
                .build();

        manager.createUser(user1);
        manager.createUser(user2);

        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
