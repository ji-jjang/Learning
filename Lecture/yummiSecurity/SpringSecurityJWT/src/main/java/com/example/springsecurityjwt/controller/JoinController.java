package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.dto.JoinDTO;
import com.example.springsecurityjwt.service.JoinService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinController {

  private final JoinService joinService;

  public JoinController(JoinService joinService) {

    this.joinService = joinService;
  }

  @PostMapping("/join")
  public String joinProcess(JoinDTO joinDTO) {

    System.out.println(joinDTO.toString());
    joinService.joinProcess(joinDTO);

    return "ok";
  }
}
