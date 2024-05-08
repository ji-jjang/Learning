package com.juny.dddstart2.lock;

public class LockManagerException extends RuntimeException {
  public LockManagerException(Exception cause) {
    super(cause);
  }
}
