package com.juny.dddstart2.order.command.domain;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.data.repository.Repository;

public interface OrderRepository extends Repository<Order, Long> {
  Optional<Order> findById(OrderNo id);

  void save(Order order);

  default OrderNo nextOrderNo() {
    int randomNo = ThreadLocalRandom.current().nextInt(900000) + 100000;
    String number = String.format("%tY%<tm%<td%<tH-%d", new Date(), randomNo);
    return new OrderNo(number);
  }
}
