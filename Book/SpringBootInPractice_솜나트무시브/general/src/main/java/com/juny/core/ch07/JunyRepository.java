package com.juny.core.ch07;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JunyRepository extends JpaRepository<Juny, Long> {

  Iterable<Juny> findByAuthor(String username);
}
