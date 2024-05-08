package com.juny.dddstart2.order.command.domain;

import com.juny.dddstart2.common.event.Event;

public class OrderCanceledEvent extends Event {
  private String orderNumber;

  public OrderCanceledEvent(String number) {
    super();
    this.orderNumber = number;
  }

  public String getOrderNumber() {
    return orderNumber;
  }
}
