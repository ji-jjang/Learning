package com.juny.dddstart2.catalog.query.product;

import com.juny.dddstart2.catalog.command.domain.category.CategoryId;
import com.juny.dddstart2.catalog.command.domain.product.Image;
import com.juny.dddstart2.catalog.command.domain.product.ProductId;
import com.juny.dddstart2.common.jpa.MoneyConverter;
import com.juny.dddstart2.common.model.Money;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ProductData {
  @EmbeddedId
  private ProductId id;

  @ElementCollection
  @CollectionTable(name = "product_category",
      joinColumns = @JoinColumn(name = "product_id"))
  private Set<CategoryId> categoryIds;

  private String name;

  @Convert(converter = MoneyConverter.class)
  private Money price;

  private String detail;

  // TODO 목록에서 사용할 것

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
      orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "product_id")
  @OrderColumn(name = "list_idx")
  private List<Image> images = new ArrayList<>();

  protected ProductData() {
  }

  public ProductData(ProductId id, String name, Money price, String detail, List<Image> images) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.detail = detail;
    this.images.addAll(images);
  }

  public ProductId getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Money getPrice() {
    return price;
  }

  public String getDetail() {
    return detail;
  }

  public List<Image> getImages() {
    return Collections.unmodifiableList(images);
  }

  public String getFirstIamgeThumbnailPath() {
    if (images == null || images.isEmpty()) return null;
    return images.get(0).getThumbnailUrl();
  }
}
