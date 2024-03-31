package core.security.chapter06;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthenticationProviderService implements AuthenticationProvider {
//
//    @Autowired
//    private JpaUserDetailsService jpaUserDetailsService;
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Autowired
//    private SCryptPasswordEncoder sCryptPasswordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        CustomUserDetails user = jpaUserDetailsService.loadUserByUsername(username);
//
//        System.out.println("user.getUser().getAlgorithm() = " + user.getUser().getAlgorithm());
//        System.out.println("user.getPassword() = " + user.getPassword());
//        System.out.println("user.getUser().getPassword() = " + user.getUser().getPassword());
//        switch (user.getUser().getAlgorithm()) {
//            case BCRYPT -> {return checkPassword(user, password, bCryptPasswordEncoder);}
//            case SCRYPT -> {return checkPassword(user, password, sCryptPasswordEncoder);}
//        }
//        throw new BadCredentialsException("Bad credentials zz");
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//
//    private Authentication checkPassword(CustomUserDetails user, String rawPassword, PasswordEncoder encoder) {
//
//        if (encoder.matches(rawPassword, user.getPassword())) {
//            System.out.println("같잖아!");
//            return new UsernamePasswordAuthenticationToken(
//                    user.getUsername(),
//                    user.getPassword(),
//                    user.getAuthorities()
//            );
//        } else {
//            System.out.println("rawPassword = " + rawPassword);
//            throw new BadCredentialsException("Bad credentials hoho");
//        }
//    }
//}
