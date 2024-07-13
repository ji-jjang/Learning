package com.example.event;

import com.example.event.user.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CouponService {

  @Async
  @EventListener
  public void sendCouponToNewUser(UserCreatedEvent event) throws InterruptedException {

    log.info("sendCouponToNewUser");
    for (int i = 0; i < 2; i++) {
      log.info("sending coupon...");
      Thread.sleep(1000);
    }
  }
}
