package com.juny.dddstart2.catalog.command.domain.category;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, CategoryId> {}
