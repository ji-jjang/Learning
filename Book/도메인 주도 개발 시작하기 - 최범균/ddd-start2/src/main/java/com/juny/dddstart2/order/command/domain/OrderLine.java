package com.juny.dddstart2.order.command.domain;

import com.juny.dddstart2.catalog.command.domain.product.ProductId;
import com.juny.dddstart2.common.jpa.MoneyConverter;
import com.juny.dddstart2.common.model.Money;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class OrderLine {
  @Embedded
  private ProductId productId;

  @Convert(converter = MoneyConverter.class)
  @Column(name = "price")
  private Money price;

  @Column(name = "quantity")
  private int quantity;

  @Convert(converter = MoneyConverter.class)
  @Column(name = "amounts")
  private Money amounts;

  protected OrderLine() {
  }

  public OrderLine(ProductId productId, Money price, int quantity) {
    this.productId = productId;
    this.price = price;
    this.quantity = quantity;
    this.amounts = calculateAmounts();
  }

  private Money calculateAmounts() {
    return price.multiply(quantity);
  }

  public ProductId getProductId() {
    return productId;
  }

  public Money getPrice() {
    return price;
  }

  public int getQuantity() {
    return quantity;
  }

  public Money getAmounts() {
    return amounts;
  }
}
