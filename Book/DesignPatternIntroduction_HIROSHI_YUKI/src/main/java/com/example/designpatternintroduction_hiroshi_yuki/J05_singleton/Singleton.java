package com.example.designpatternintroduction_hiroshi_yuki.J05_singleton;

public class Singleton {
  private static Singleton singleton = new Singleton();

  private Singleton() {
    System.out.println("인스턴스를 생성했습니다.");
  }

  public static Singleton getInstance() {
    return singleton;
  }
}
