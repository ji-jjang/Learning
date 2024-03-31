package core.security.chapter11_01;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private ApplicationContext applicationContext;

  private AuthenticationManager getAuthenticationManager() {
    return applicationContext.getBean(AuthenticationManager.class);
  }

  @Value("${jwt.signing.key}")
  private String signingKey;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    AuthenticationManager manager = getAuthenticationManager();
    String username = request.getHeader("username");
    String password = request.getHeader("password");
    String code = request.getHeader("code");
    if (code == null) {
      UsernamePasswordAuthentication a = new UsernamePasswordAuthentication(username, password);
      manager.authenticate(a);
    } else {
      Authentication a = new OtpAuthentication(username, code);

      a = manager.authenticate(a);

      SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));

      String jwt = Jwts.builder().claims(Map.of("username", username))
          .signWith(key)
          .compact();
      response.setHeader("Authorization", jwt);
    }
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    return !request.getServletPath().equals("/login");
  }

}
