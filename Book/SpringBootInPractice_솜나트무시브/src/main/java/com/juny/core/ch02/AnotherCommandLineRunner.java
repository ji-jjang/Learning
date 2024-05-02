package com.juny.core.ch02;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(0)
@Slf4j
public class AnotherCommandLineRunner implements CommandLineRunner {
  @Override
  public void run(String... args) throws Exception {
    log.info("AnotherCommandLineRunner executed as a Spring Compoent");
  }
}
