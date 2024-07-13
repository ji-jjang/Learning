package com.example.event;

import com.example.event.user.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

  @Async
  @EventListener
  public void sendEmailToNewUser(UserCreatedEvent event) throws InterruptedException {
    log.info("sendEmailToNewUser");
    for (int i = 0; i < 3; i++) {
      log.info("sending email...");
      Thread.sleep(1000);
    }
  }
}
