package com.example.designpatternintroduction_hiroshi_yuki.J05_singleton;

public class Main {
  public static void main(String[] args) {
    System.out.println("Start");
    Singleton obj1 = Singleton.getInstance();
    Singleton obj2 = Singleton.getInstance();
    if (obj1 == obj2) {
      System.out.println("같은 인스턴스 입니다.");
    } else {
      System.out.println("같은 인스턴스가 아닙니다.");
    }
    System.out.println("End");
  }
}
