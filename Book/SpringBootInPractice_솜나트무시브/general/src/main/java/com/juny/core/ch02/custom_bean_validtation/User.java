package com.juny.core.ch02.custom_bean_validtation;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class User {

  private String username;

  @Password
  private String password;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }
}
