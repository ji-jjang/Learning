package com.juny.core.ch05.repository;

import com.juny.core.ch05.model.ApplicationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {

  ApplicationUser findByUsername(String username);
}
