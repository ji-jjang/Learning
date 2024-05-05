package com.juny.core.ch05.exception;

public class InvalidVerificationCode extends RuntimeException {

  private static final long serialVersionUID = 7777711105269954777L;

  public InvalidVerificationCode(String message) {
    super(message);
  }
}
