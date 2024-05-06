package com.juny.core.ch04;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Test {
  @Value("${info.build.name}")
  private String name;

  public static void main(String[] args){
    Test test = new Test();
    System.out.println(test.name);
  }
}
