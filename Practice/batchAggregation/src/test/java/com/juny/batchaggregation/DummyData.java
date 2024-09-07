package com.juny.batchaggregation;

import com.juny.batchaggregation.entity.Order;
import com.juny.batchaggregation.entity.OrderStatus;
import com.juny.batchaggregation.repository.OrderRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class DummyData {

  @Autowired
  private OrderRepository orderRepository;

  @Transactional
  @Rollback(value = false)
  @Test
  public void createOrder() {
    for(int i = 0; i < 100; i++) {
      Order order =
          new Order(
              "order " + i,
              OrderStatus.결제대기,
              1L * 10000 * i,
              LocalDateTime.of(2023, 11, 11, 11, 11));
      orderRepository.save(order);
    }
  }
}
