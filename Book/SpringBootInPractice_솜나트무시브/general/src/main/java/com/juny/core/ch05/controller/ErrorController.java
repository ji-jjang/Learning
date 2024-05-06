package com.juny.core.ch05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorController {

  @RequestMapping(value = "/accessDenied", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
  public String accessDenied() {
    return "accessDenied";
  }
}
