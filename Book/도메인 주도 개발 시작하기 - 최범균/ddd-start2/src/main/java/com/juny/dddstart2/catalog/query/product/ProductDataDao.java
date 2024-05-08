package com.juny.dddstart2.catalog.query.product;

import com.juny.dddstart2.catalog.command.domain.category.CategoryId;
import com.juny.dddstart2.catalog.command.domain.product.ProductId;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDataDao extends JpaRepository {
  Optional<ProductData> findById(ProductId id);

  Page<ProductData> findByCategoryIdsContains(CategoryId id, Pageable pageable);
}
