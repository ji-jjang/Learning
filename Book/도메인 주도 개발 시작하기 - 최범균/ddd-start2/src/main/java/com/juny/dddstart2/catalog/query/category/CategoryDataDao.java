package com.juny.dddstart2.catalog.query.category;

import com.juny.dddstart2.catalog.command.domain.category.CategoryId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDataDao extends JpaRepository<CategoryData, Long> {
  Optional<CategoryData> findById(CategoryId id);

  List<CategoryData> findAll();
}
