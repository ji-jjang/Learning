package com.juny.dddstart2.catalog.command.domain.product;

import com.juny.dddstart2.catalog.command.domain.category.CategoryId;
import com.juny.dddstart2.common.jpa.MoneyConverter;
import com.juny.dddstart2.common.model.Money;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;

@Entity
@Table(name = "product")
@Getter
public class Product {
  @EmbeddedId
  private ProductId id;

  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "product_category",
  joinColumns = @JoinColumn(name = "product_id"))
  private Set<CategoryId> categoryIds;

  private String name;

  @Convert(converter = MoneyConverter.class)
  private Money price;

  private String detail;

  @OneToMany(
      cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  @OrderColumn(name = "list_idx")
  private List<Image> images = new ArrayList<>();

  protected Product() {}

  public Product(ProductId id, String name, Money price, String detail, List<Image> images) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.detail = detail;
    this.images = images;
  }

  public void changeImages(List<Image> newImages) {
    images.clear();
    images.addAll(newImages);
  }

  public String getFirstImageThumbnailPath() {
    if (images == null || images.isEmpty()) return null;
    return images.get(0).getThumbnailUrl();
  }

  public void addCategory(CategoryId categoryId) {
    if (this.categoryIds == null) {
      this.categoryIds = new HashSet<>();
    }
    this.categoryIds.add(categoryId);
  }
}
