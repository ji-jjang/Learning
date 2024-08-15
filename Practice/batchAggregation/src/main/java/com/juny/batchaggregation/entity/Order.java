package com.juny.batchaggregation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column
  private String name;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @Column
  private Long totalPrice;

  @Column
  private LocalDateTime createdAt;

  public Order(String name, OrderStatus status, Long totalPrice) {
    this.name = name;
    this.status = status;
    this.totalPrice = totalPrice;
    this.createdAt = LocalDateTime.now();
  }
  public Order(String name, OrderStatus status, Long totalPrice, LocalDateTime createdAt) {
    this.name = name;
    this.status = status;
    this.totalPrice = totalPrice;
    this.createdAt = createdAt;
  }

  public void ChangeOrderStatus(OrderStatus status) {
    this.status = status;
  }
}
