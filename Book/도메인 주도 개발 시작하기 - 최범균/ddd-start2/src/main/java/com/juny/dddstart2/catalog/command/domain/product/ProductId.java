package com.juny.dddstart2.catalog.command.domain.product;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;

@Embeddable
@Access(AccessType.FIELD)
@Getter
public class ProductId implements Serializable {
  @Column(name = "product_id")
  private String id;

  protected ProductId() {}

  public ProductId(String id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    ProductId productId = (ProductId) obj;
    return Objects.equals(id, productId.id);
  }

  public static ProductId of(String id) {
    return new ProductId(id);
  }
}
