package com.juny.core.ch05.service;

import com.juny.core.ch05.dto.UserDto;
import com.juny.core.ch05.model.ApplicationUser;
import com.juny.core.ch05.repository.ApplicationUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {


  private final ApplicationUserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  public DefaultUserService(ApplicationUserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public ApplicationUser createUser(UserDto userDto) {
    ApplicationUser applicationUser = new ApplicationUser();
    applicationUser.setFirstName(userDto.getFirstName());
    applicationUser.setLastName(userDto.getLastName());
    applicationUser.setEmail(userDto.getEmail());
    applicationUser.setUsername(userDto.getUsername());
    applicationUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
    applicationUser.setVerified(true);

    return userRepository.save(applicationUser);
  }

  @Override
  public ApplicationUser save(ApplicationUser applicationUser) {
    return userRepository.save(applicationUser);
  }

  @Override
  public ApplicationUser findByUsername(String username) {
    return userRepository.findByUsername(username);
  }
}
