package com.juny.jsppractice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

  @GetMapping("/")
  public String home() {

    return "/welcome";
  }

  @GetMapping("/script")
  public String script() {

    return "/chapter2/script";
  }

  @GetMapping("/count")
  public String count() {

    return "/chapter2/count";
  }
}
