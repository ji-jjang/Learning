package com.practice.react.service;

import com.practice.react.model.UserEntity;
import com.practice.react.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public UserEntity create(final UserEntity userEntity) {
    if (userEntity == null || userEntity.getEmail() == null) {
      throw new RuntimeException("Invalid arguemnts");
    }
    final String email = userEntity.getEmail();
    if (userRepository.existsByEmail(email)) {
      log.warn("Email already exists {}", email);
      throw new RuntimeException("Email alreay exist");
    }
    return userRepository.save(userEntity);
  }

  public UserEntity getByCredentials(final String email, final String password) {
    return userRepository.findByEmailAndPassword(email, password);
  }
}
