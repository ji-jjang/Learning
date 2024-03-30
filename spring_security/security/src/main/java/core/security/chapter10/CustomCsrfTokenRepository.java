package core.security.chapter10;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.security.web.csrf.DeferredCsrfToken;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;


@Component
@Slf4j
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

  @Autowired
  private JpaTokenRepository jpaTokenRepository;

  @Override
  public CsrfToken generateToken(HttpServletRequest request) {
    log.info("generateToken");
    String uuid = UUID.randomUUID().toString();
    return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
  }

  @Override
  public void saveToken(CsrfToken csrfToken, HttpServletRequest request, HttpServletResponse response) {
    log.info("saveToken");
    String identifier = request.getHeader("X-IDENTIFIER");
    System.out.println("identifier = " + identifier);
    Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);

    if (existingToken.isPresent()) {
      Token token = existingToken.get();
      token.setToken(csrfToken.getToken());
    } else {
      Token token = new Token();
      token.setToken(csrfToken.getToken());
      token.setIdentifier(identifier);
      jpaTokenRepository.save(token);
    }
  }

  @Override
  public CsrfToken loadToken(HttpServletRequest request) {
    log.info("loadToken");
    String identifier = request.getHeader("X-IDENTIFIER");
    System.out.println("identifier = " + identifier);
    Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);

    if (existingToken.isPresent()) {
      Token token = existingToken.get();
      System.out.println("token = " + token);
      return new DefaultCsrfToken(
          "X-CSRF-TOKEN",
          "_csrf",
          token.getToken()
      );
    }
    return null;
  }

  @Override
  public DeferredCsrfToken loadDeferredToken(HttpServletRequest request, HttpServletResponse response) {
    return CsrfTokenRepository.super.loadDeferredToken(request, response);
  }
}
