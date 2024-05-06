package com.juny.core.ch05.service;

import com.juny.core.ch05.dto.UserDto;
import com.juny.core.ch05.model.ApplicationUser;

public interface UserService {
  ApplicationUser createUser(UserDto userDto);
  ApplicationUser save(ApplicationUser applicationUser);
  ApplicationUser findByUsername(String username);

  ApplicationUser findOrCreateUser(String email, String name);
}
