package com.juny.core.ch05.repository;

import com.juny.core.ch05.model.TotpDetails;
import org.springframework.data.repository.CrudRepository;

public interface TotpRepository extends CrudRepository<TotpDetails, Long> {

  TotpDetails findByUsername(String username);
  boolean existsByUsername(String username);
  Long deleteByUsername(String username);
}
