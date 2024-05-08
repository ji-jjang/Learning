package com.juny.dddstart2.integration;

import com.juny.dddstart2.eventstore.api.EventEntry;

public interface EventSender {
  void send(EventEntry event);
}
