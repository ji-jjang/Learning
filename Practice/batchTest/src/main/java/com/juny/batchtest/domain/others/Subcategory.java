package com.juny.batchtest.domain.others;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "subcategories")
public class Subcategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "subcategory_name", nullable = false)
  private String subcategoryName;

  @Column(name = "category_id", nullable = true)
  private Long categoryId;

  @ManyToOne
  @JoinColumn(name = "parent_category_id")
  private Category category;

  @OneToMany(mappedBy = "subcategory")
  private List<Product> products = new ArrayList<>();

  public void update(String subcategoryName, Category category, Long categoryId) {
    this.subcategoryName = subcategoryName;
    this.category = category;
    this.categoryId = categoryId;
  }

  @Builder
  public Subcategory(String subcategoryName, Long categoryId) {
    this.subcategoryName = subcategoryName;
    this.categoryId = categoryId;
    this.category = new Category();
    this.category.setId(categoryId);
  }
}

