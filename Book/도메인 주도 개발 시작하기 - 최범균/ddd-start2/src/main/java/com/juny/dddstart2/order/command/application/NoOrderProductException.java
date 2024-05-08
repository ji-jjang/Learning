package com.juny.dddstart2.order.command.application;

public class NoOrderProductException extends RuntimeException{
  private String productId;

  public NoOrderProductException(String productId) {
    this.productId = productId;
  }

  public String getProductId() {
    return productId;
  }
}
