package core.security.chapter07;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class ProjectConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.httpBasic(Customizer.withDefaults())
//                .authorizeRequests()
//                .anyRequest()
//                .denyAll();
////                .hasRole("admin"); // ROLE 접두사가 붙는다
////                .access("hasAuthority('read') and !hasAuthority('delete')");
////                .hasAuthority("WRITE");
//
//        return http.build();
////        http.formLogin(
////                        a -> a.defaultSuccessUrl("/main", true)
////                )
////                .authorizeRequests()
////                .anyRequest()
////                .authenticated();
////        return http.build();
//    }
//
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//
//        UserDetails user1 = User.withUsername("john")
//                .password("1234")
//                .roles("admin")
////                .authorities("ROLE_admin") // ROLE 접두사를 붙인다.
//                .build();
//
//        UserDetails user2 = User.withUsername("jane")
//                .password("1234")
//                .roles("manager")
////                .authorities("ROLE_manager")
//                .build();
//
//        manager.createUser(user1);
//        manager.createUser(user2);
//
//        return manager;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//}
