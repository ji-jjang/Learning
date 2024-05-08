package com.juny.dddstart2.catalog.query.category;

import com.juny.dddstart2.catalog.command.domain.category.CategoryId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "category")
@Getter
public class CategoryData {
  @EmbeddedId
  private CategoryId id;

  @Column(name = "name")
  private String name;

  protected CategoryData() {}

  public CategoryData(CategoryId id, String name) {
    this.id = id;
    this.name = name;
  }
}
