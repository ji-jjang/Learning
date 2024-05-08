package com.juny.dddstart2.order.command.application;

import com.juny.dddstart2.order.NoOrderException;
import com.juny.dddstart2.order.command.domain.CancelPolicy;
import com.juny.dddstart2.order.command.domain.Canceller;
import com.juny.dddstart2.order.command.domain.Order;
import com.juny.dddstart2.order.command.domain.OrderNo;
import com.juny.dddstart2.order.command.domain.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CancelOrderService {
  private OrderRepository orderRepository;
  private CancelPolicy cancelPolicy;

  public CancelOrderService(OrderRepository orderRepository,
      CancelPolicy cancelPolicy) {
    this.orderRepository = orderRepository;
    this.cancelPolicy = cancelPolicy;
  }

  @Transactional
  public void cancel(OrderNo orderNo, Canceller canceller) {
    Order order = orderRepository.findById(orderNo)
        .orElseThrow(() -> new NoOrderException());
    if (!cancelPolicy.hasCancellationPermission(order, canceller)) {
      throw new NoCancellablePermission();
    }
    order.cancel();
  }

}
