package com.juny.dddstart2.catalog.command.domain.category;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "category")
@Getter
public class Category {
  @EmbeddedId
  private CategoryId categoryId;

  @Column(name = "name")
  private String name;

  protected Category() {}

  public Category(CategoryId categoryId, String name) {
    this.categoryId = categoryId;
    this.name = name;
  }
}
