package com.juny.batchpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BatchPracticeApplication {

  public static void main(String[] args) {
    SpringApplication.run(BatchPracticeApplication.class, args);
  }

}
