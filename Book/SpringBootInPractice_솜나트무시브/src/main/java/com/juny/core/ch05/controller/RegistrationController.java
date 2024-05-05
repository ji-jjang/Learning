package com.juny.core.ch05.controller;

import com.juny.core.ch05.dto.RecaptchaDto;
import com.juny.core.ch05.dto.UserDto;
import com.juny.core.ch05.event.UserRegistrationEvent;
import com.juny.core.ch05.model.ApplicationUser;
import com.juny.core.ch05.service.GoogleRecaptchaService;
import com.juny.core.ch05.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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

  @Autowired
  private ApplicationEventPublisher eventPublisher;

  @Autowired
  private GoogleRecaptchaService captchaService;

  @GetMapping("/adduser")
  public String register(Model model) {
    model.addAttribute("user", new UserDto());
    return "add-user";
  }

  @PostMapping("/adduser")
  public String register(@Valid @ModelAttribute("user") UserDto userDto, HttpServletRequest request, BindingResult result) {
    if(result.hasErrors()) {
      return "add-user";
    }

    String response = request.getParameter("g-recaptcha-response");
    if (response == null){
      return "add-user";
    }
    String ip = request.getRemoteAddr();
    RecaptchaDto recaptchaDto = captchaService.verify(ip, response);
    if (!recaptchaDto.isSuccess()){
      return "redirect:adduser?incorrectCAPCHA";
    }

    ApplicationUser applicationUser = userService.createUser(userDto);
    eventPublisher.publishEvent(new UserRegistrationEvent(applicationUser));
//    if("Y".equalsIgnoreCase(emailVerification)) {
//      eventPublisher.publishEvent(new UserRegistrationEvent(applicationUser));
//    }
    return "redirect:adduser?validate";
//    return "redirect:adduser?success";
  }

}
