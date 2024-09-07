package com.example.mybatis.user.controller;

import com.example.mybatis.user.domain.User;
import com.example.mybatis.user.repository.mybatis.UserMapper;
import com.example.mybatis.user.repository.jpa.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserMapper userMapper;

  private final UserRepository userRepository;

  @GetMapping("/users")
  public List<User> getUsers() {
    return userMapper.findAll();
  }

  @GetMapping("/users/jpa")
  public List<User> getUsersByJpa() {
    return userRepository.findAll();
  }

}
