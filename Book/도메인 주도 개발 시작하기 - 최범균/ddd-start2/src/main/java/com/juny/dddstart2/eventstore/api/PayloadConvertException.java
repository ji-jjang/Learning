package com.juny.dddstart2.eventstore.api;

public class PayloadConvertException extends RuntimeException {
  public PayloadConvertException(Exception e) {
    super(e);
  }
}
