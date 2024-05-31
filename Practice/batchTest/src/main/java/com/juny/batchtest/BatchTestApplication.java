package com.juny.batchtest;

import com.juny.batchtest.config.EnvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@PropertySource(value = {
    "classpath:env.yml",
}, factory = EnvConfig.class)
@EnableScheduling
public class BatchTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(BatchTestApplication.class, args);
  }

}
