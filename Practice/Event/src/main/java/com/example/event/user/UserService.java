package com.example.event.user;

import jakarta.transaction.Transactional;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

  private final UserRepository userRepository;

  private final ApplicationEventPublisher eventPublisher;

  public User register(ReqCreateUser req) throws InterruptedException {

    User savedUser = userRepository.save(new User(req.name(), req.email(), req.password(), req.recommender()));

    eventPublisher.publishEvent(new UserCreatedEvent(req.email(), req.recommender()));

    return savedUser;
  }
}
