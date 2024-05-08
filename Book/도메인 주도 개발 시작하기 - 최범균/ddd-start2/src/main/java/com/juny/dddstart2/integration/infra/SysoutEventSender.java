package com.juny.dddstart2.integration.infra;

import com.juny.dddstart2.eventstore.api.EventEntry;
import com.juny.dddstart2.integration.EventSender;
import org.springframework.stereotype.Component;

@Component
public class SysoutEventSender implements EventSender {
  @Override
  public void send(EventEntry event) {
    System.out.println("EventSender send event : " + event);
  }
}
