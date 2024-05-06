package com.juny.core.ch07;

import java.util.Map;
import java.util.Optional;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ReadingConverter
@RequestMapping("/courses/")
@RestController
public class JunyController {

  private final JunyRepository junyRepository;

  public JunyController(JunyRepository junyRepository) {
    this.junyRepository = junyRepository;
  }

  @GetMapping
  public Iterable<Juny> getAllJunys(@AuthenticationPrincipal Jwt jwt) {
    String author = jwt.getClaim("user_name");
    return junyRepository.findByAuthor(author);
  }

  @GetMapping("{id}")
  public Optional<Juny> getJunyById(@PathVariable("id") Long id) {
    return junyRepository.findById(id);
  }

  @PostMapping
  public Juny createJuny(@RequestBody String name, @AuthenticationPrincipal Jwt jwt) {
    Juny juny = new Juny(null, name, jwt.getClaim("user_name"));
    return junyRepository.save(juny);
  }
}
