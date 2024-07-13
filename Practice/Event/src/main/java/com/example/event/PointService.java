package com.example.event;

import com.example.event.user.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PointService {

  @Async
  @EventListener
  public void chargePoint(UserCreatedEvent event) throws InterruptedException {

    if (event.recommender() == null || event.recommender().isEmpty()) {
      log.info("referrer is empty or null : {}", event.recommender());
      return;
    }

    log.info("charge point");
    for (int i = 0; i < 2; i++) {
      log.info("charging point...");
      Thread.sleep(1000);
    }
  }
}
