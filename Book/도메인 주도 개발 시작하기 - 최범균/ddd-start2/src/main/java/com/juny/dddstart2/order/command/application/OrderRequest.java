package com.juny.dddstart2.order.command.application;

import com.juny.dddstart2.member.command.domain.MemberId;
import com.juny.dddstart2.order.command.domain.ShippingInfo;
import java.util.List;

public class OrderRequest {
  private List<OrderProduct> orderProducts;
  private MemberId ordererMemberId;
  private ShippingInfo shippingInfo;

  public List<OrderProduct> getOrderProducts() {
    return orderProducts;
  }

  public void setOrderProducts(List<OrderProduct> orderProducts) {
    this.orderProducts = orderProducts;
  }

  public MemberId getOrdererMemberId() {
    return ordererMemberId;
  }

  public void setOrdererMemberId(MemberId ordererMemberId) {
    this.ordererMemberId = ordererMemberId;
  }

  public ShippingInfo getShippingInfo() {
    return shippingInfo;
  }

  public void setShippingInfo(ShippingInfo shippingInfo) {
    this.shippingInfo = shippingInfo;
  }
}
