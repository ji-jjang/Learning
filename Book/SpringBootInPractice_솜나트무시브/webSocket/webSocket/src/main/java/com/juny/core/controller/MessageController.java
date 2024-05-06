package com.juny.core.controller;

import com.juny.core.model.InputMessage;
import com.juny.core.model.OutputMessage;
import java.time.Clock;
import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class MessageController {

  @MessageMapping("/chat")
  @SendTo("/topic/messages")
  public OutputMessage message(InputMessage message) {
    log.info("Input Message "+message);
    return OutputMessage.builder().time(Instant.now(Clock.systemDefaultZone())).content(message.getContent()).build();
  }
}
