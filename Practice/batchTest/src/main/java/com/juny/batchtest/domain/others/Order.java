package com.juny.batchtest.domain.others;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Long id;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "deliveries_id")
  private Deliveries deliveries;

  private LocalDateTime orderDate;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  // User 매핑
  private Long userId;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderItem> orderItems = new ArrayList<>();

  private Long totalPrice;

  //양방향
  public void addOrderItem(OrderItem orderItem){
    orderItems.add(orderItem);
    orderItem.setOrder(this);
  }

  public void setDeliveries(Deliveries deliveries){
    this.deliveries = deliveries;
    if (deliveries != null) {
      deliveries.setOrder(this);
    }
  }

  public Order(Deliveries deliveries, LocalDateTime orderDate, OrderStatus orderStatus, Long userId,
      List<OrderItem> orderItems, Long totalPrice) {
    this.deliveries = deliveries;
    this.orderDate = orderDate;
    this.orderStatus = orderStatus;
    this.userId = userId;
    this.orderItems = orderItems;
    this.totalPrice = totalPrice;
  }

  // 주문 생성
  public static Order createOrder(Long userId, Deliveries delivery, List<OrderItem> orderItems){
    Order order = new Order();
    order.setUserId(userId);

    order.setDeliveries(delivery);

    for(OrderItem orderItem : orderItems){
      order.addOrderItem(orderItem);
    }
    order.setOrderStatus(OrderStatus.ORDER);
    order.setOrderDate(LocalDateTime.now().minusDays(1).minusMinutes(30));
    order.setTotalPrice(50000L);

    return order;
  }
}
