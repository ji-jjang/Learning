package com.juny.batchtest.domain.others;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_item")
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_item_id")
  private Long id;

  private int orderPrice;

  private int quantity;

  // OrderItem 연관관계
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  // 추후 장바구니 구현 매핑

  public static OrderItem createOrderItem(Product product, int orderPrice, int quantity) {
    OrderItem orderItem = new OrderItem();
    orderItem.setProduct(product);
    orderItem.setOrderPrice(orderPrice);
    orderItem.setQuantity(quantity);

    return orderItem;
  }
}