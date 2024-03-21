//package core.security.chapter03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.DefaultLdapUsernameToDnMapper;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.LdapUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.util.List;

//@Configuration
//public class ProjectConfig {
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        DefaultSpringSecurityContextSource cs = new DefaultSpringSecurityContextSource(
//                "ldap://127.0.0.1:33389/dc=springframework,dc=org");
//        cs.afterPropertiesSet();
//
//        LdapUserDetailsManager manager = new LdapUserDetailsManager(cs);
//
//        manager.setUsernameMapper(new DefaultLdapUsernameToDnMapper("ou=groups","uid"));
//        manager.setGroupSearchBase("ou=groups");
//        return manager;
//    }
//
////    @Bean
////    public UserDetailsService userDetailsService(DataSource dataSource) {
////        String usersByUsernameQuery = "select username, password, enabled from users where username = ?";
////        String authsByUserQuery = "select username, authority from spring.authorities where username = ?";
////
////        var userDetailsManager = new JdbcUserDetailsManager(dataSource);
////        userDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
////        userDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);
////        return userDetailsManager;
////    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//}
