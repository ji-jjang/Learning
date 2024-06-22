package com.practice.react.persistence;

import com.practice.react.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
  UserEntity findByEmail(String email);

  Boolean existsByEmail(String email);

  UserEntity findByEmailAndPassword(String email, String password);
}
