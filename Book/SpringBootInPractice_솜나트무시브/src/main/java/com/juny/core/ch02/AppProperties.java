package com.juny.core.ch02;

import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.List;

//@ConfigurationProperties("app.sbip.ct")
@Getter
@ToString
public class AppProperties {

  private final String name;
  private final String ip;
  private final int port;
  private final Security security;

  public AppProperties(String name, String ip, int port, Security security) {
    this.name = name;
    this.ip = ip;
    this.port = port;
    this.security = security;
  }

  @Getter
  @ToString
  public static class Security {
    private boolean enabled;
    private final String token;
    private final List<String> roles;

    public Security(boolean enabled, String token, List<String> roles) {
      this.enabled = enabled;
      this.token = token;
      this.roles = roles;
    }
  }

}
