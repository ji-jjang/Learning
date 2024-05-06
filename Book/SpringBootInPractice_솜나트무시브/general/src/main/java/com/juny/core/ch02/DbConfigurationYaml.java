package com.juny.core.ch02;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "db")
@ToString
@Getter
@Setter
public class DbConfigurationYaml {

  private String user;
  private String password;
}
