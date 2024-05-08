package com.juny.dddstart2.order.command.application;

import com.juny.dddstart2.order.command.domain.ShippingInfo;

public class ChangeShippingRequest {
  private String number;
  private ShippingInfo shippingInfo;

  public ChangeShippingRequest(String number, ShippingInfo shippingInfo) {
    this.number = number;
    this.shippingInfo = shippingInfo;
  }

  public String getNumber() {
    return number;
  }

  public ShippingInfo getShippingInfo() {
    return shippingInfo;
  }
}
