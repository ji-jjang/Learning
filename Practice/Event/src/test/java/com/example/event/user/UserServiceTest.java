package com.example.event.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

  @Autowired UserService userService;
  @Autowired
  private UserRepository userRepository;

  @Test
  void register() throws InterruptedException {

    userService.register(new ReqCreateUser("jiny", "jiny@gmail.com", "1234", "juny"));

    assertThat(userRepository.findAll().size()).isEqualTo(1);
  }
}