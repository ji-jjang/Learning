package com.juny.securityjwtoauth2.repository;

import com.juny.securityjwtoauth2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  UserEntity findByUsername(String username);
}
