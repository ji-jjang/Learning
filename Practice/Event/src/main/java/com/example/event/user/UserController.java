package com.example.event.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

  private final UserService userService;

  @PostMapping("/api/v1/register")
  public ResponseEntity<User> register(@RequestBody ReqCreateUser req) throws InterruptedException {

    User user = userService.register(req);

    return new ResponseEntity<>(user, HttpStatus.OK);
  }
}
