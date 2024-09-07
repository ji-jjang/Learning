package com.juny.configclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

  @GetMapping("/ms1/first")
  public String mainP() {

    return "ms1 first";
  }
}
