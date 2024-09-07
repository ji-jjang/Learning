package com.juny.batchaggregation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BatchAggregationApplication {

  public static void main(String[] args) {
    SpringApplication.run(BatchAggregationApplication.class, args);
  }

}
