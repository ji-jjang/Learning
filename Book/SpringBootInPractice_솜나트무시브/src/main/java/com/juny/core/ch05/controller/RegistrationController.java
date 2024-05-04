package com.juny.core.ch05.controller;

import com.juny.core.ch05.dto.UserDto;
import com.juny.core.ch05.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

  @Autowired
  private UserService userService;

  @GetMapping("/adduser")
  public String register(Model model) {
    model.addAttribute("user", new UserDto());
    return "add-user";
  }

  @PostMapping("/adduser")
  public String register(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result) {
    if(result.hasErrors()) {
      return "add-user";
    }
    userService.createUser(userDto);
    return "redirect:adduser?success";
  }

}
