package com.example.servlet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleMVC {

  @GetMapping("/simpleMVC")
  public String getSimpleMvc() {
    System.out.println("simpleMVC");
    return "simple MVC";
  }
}
