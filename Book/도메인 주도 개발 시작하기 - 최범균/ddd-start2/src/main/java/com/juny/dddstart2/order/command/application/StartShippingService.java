package com.juny.dddstart2.order.command.application;

import com.juny.dddstart2.common.VersionConflictException;
import com.juny.dddstart2.order.NoOrderException;
import com.juny.dddstart2.order.command.domain.Order;
import com.juny.dddstart2.order.command.domain.OrderNo;
import com.juny.dddstart2.order.command.domain.OrderRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StartShippingService {
  private OrderRepository orderRepository;

  public StartShippingService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Transactional
  public void startShipping(StartShippingRequest req) {
    Optional<Order> orderOpt = orderRepository.findById(new OrderNo(req.getOrderNumber()));
    Order order = orderOpt.orElseThrow(() -> new NoOrderException());
    if (order.matchVersion(req.getVersion())) {
      throw new VersionConflictException();
    }
    order.startShipping();
  }
}

