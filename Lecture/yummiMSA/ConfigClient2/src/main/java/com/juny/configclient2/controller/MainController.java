package com.juny.configclient2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

  @GetMapping("/ms2/second")
  public String mainP() {

    return "ms2 second";
  }
}