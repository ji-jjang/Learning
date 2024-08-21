package com.juny.jpaaudit.config;

import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class TestAuditAwareConfig {

  @Bean
  @Primary
  public AuditorAware<String> testAuditorAware() {
    return () -> Optional.of("jjiny");  // 테스트에서 사용될 사용자 이름
  }
}
