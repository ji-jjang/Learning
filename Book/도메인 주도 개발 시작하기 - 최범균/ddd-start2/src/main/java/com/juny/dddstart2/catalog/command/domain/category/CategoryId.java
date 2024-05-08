package com.juny.dddstart2.catalog.command.domain.category;

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
public class CategoryId implements Serializable {
  @Column(name = "category_id")
  private Long value;

  protected CategoryId() {}

  public CategoryId(Long value) {
    this.value = value;
  }

  @Override
  public int hashCode() {
    return value != null ? value.hashCode() : 0;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    CategoryId that = (CategoryId) obj;
    return Objects.equals(value, that.value);
  }

  public static CategoryId of(Long value) {
    return new CategoryId(value);
  }
}