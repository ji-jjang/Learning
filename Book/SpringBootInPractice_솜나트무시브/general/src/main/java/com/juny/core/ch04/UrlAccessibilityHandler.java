package com.juny.core.ch04;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UrlAccessibilityHandler {

  @Value("${api.url:https://dog.ceo/}")
  private String url;

//  @EventListener(classes = ContextRefreshedEvent.class)
  public void listen() {
    throw new UrlNotAccessibleException(url);
  }
}
