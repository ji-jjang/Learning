package core.security.chapter05;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        Optional<? extends GrantedAuthority> auth = authorities.stream().filter(a -> a.getAuthority().equals("read"))
                .findFirst();

        if (auth.isPresent()) {
            System.out.println("isPresent");
            response.sendRedirect("/home");
        } else {
            System.out.println("isNotPresent");
            response.sendRedirect("/error");
        }
    }
}
