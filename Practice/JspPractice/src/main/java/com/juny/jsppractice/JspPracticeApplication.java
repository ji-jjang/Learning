package com.juny.jsppractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class JspPracticeApplication {

  public static void main(String[] args) {
    SpringApplication.run(JspPracticeApplication.class, args);
  }
}
