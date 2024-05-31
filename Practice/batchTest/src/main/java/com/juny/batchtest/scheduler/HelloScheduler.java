package com.juny.batchtest.scheduler;

import java.time.LocalDateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HelloScheduler {

//  @Scheduled(cron = "5 * * * * *")
  public void Hello() {
    System.out.println("Hello Scheduler~");
  }

}
