package com.practice.react.controller;


import com.practice.react.dto.ResponseDTO;
import com.practice.react.dto.UserDTO;
import com.practice.react.model.UserEntity;
import com.practice.react.security.TokenProvider;
import com.practice.react.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private TokenProvider tokenProvider;

  private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody UserDTO userDto) {
    try {
      UserEntity user = UserEntity.builder()
          .email(userDto.getEmail())
          .username(userDto.getUsername())
          .password(passwordEncoder.encode(userDto.getPassword()))
          .build();
      UserEntity registeredUser = userService.create(user);
      UserDTO responseUserDTO = UserDTO.builder()
          .email(registeredUser.getEmail())
          .id(registeredUser.getId())
          .username(registeredUser.getUsername())
          .build();
      return ResponseEntity.ok().body(responseUserDTO);
    } catch (Exception e) {
      ResponseDTO<Object> responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
      return ResponseEntity
          .badRequest()
          .body(responseDTO);
    }
  }

  @PostMapping("/signin")
  public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {
    UserEntity user = userService.getByCredentials(userDTO.getEmail(), userDTO.getPassword(), passwordEncoder);
    if (user != null) {
      String token = tokenProvider.create(user);
      UserDTO responseUserDTO = UserDTO.builder()
          .email(user.getEmail())
//          .username(user.getUsername())
//          .password(user.getPassword())
          .id(user.getId())
          .token(token)
          .build();
      return ResponseEntity.ok().body(responseUserDTO);
    } else {
      ResponseDTO<Object> responseDTO = ResponseDTO.builder()
          .error("Login failed.")
          .build();
      return ResponseEntity
          .badRequest()
          .body(responseDTO);
    }
  }
}
