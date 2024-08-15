package com.juny.batchaggregation.repository;

import com.juny.batchaggregation.entity.Order;
import com.juny.batchaggregation.entity.OrderStatus;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
  Page<Order> findByStatusAndCreatedAtBefore(OrderStatus status, LocalDateTime createdAt, Pageable pageable);
}
