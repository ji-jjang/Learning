package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.dto.JoinDTO;
import com.example.springsecurityjwt.entity.UserEntity;
import com.example.springsecurityjwt.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }
  public void joinProcess(JoinDTO joinDTO) {
    String username = joinDTO.getUsername();
    String password = joinDTO.getPassword();

    Boolean isExist = userRepository.existsByUsername(username);

    if (isExist) {
      throw new IllegalArgumentException("이미 존재하는 회원 이름입니다.");
    }

    UserEntity data = new UserEntity();

    data.setUsername(username);
    data.setPassword(bCryptPasswordEncoder.encode(password));
    data.setRole("ROLE_ADMIN");

    userRepository.save(data);
  }
}
