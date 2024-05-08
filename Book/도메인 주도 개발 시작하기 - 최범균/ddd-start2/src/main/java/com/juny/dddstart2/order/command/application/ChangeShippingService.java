package com.juny.dddstart2.order.command.application;

import com.juny.dddstart2.order.NoOrderException;
import com.juny.dddstart2.order.command.domain.Order;
import com.juny.dddstart2.order.command.domain.OrderNo;
import com.juny.dddstart2.order.command.domain.OrderRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChangeShippingService {
  private OrderRepository orderRepository;

  public ChangeShippingService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Transactional
  public void changeShipping(ChangeShippingRequest changeReq) {
    Optional<Order> orderOpt = orderRepository.findById(new OrderNo(changeReq.getNumber()));
    Order order = orderOpt.orElseThrow(() -> new NoOrderException());
    order.changeShippingInfo(changeReq.getShippingInfo());
  }
}
