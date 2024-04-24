package com.example.designpatternintroduction_hiroshi_yuki.J02_adapter.delegator;

public class Banner {
  private String string;

  public Banner(String string) {
    this.string = string;
  }

  public void showWithParen() {
    System.out.println("(" + string + ")");
  }

  public void showWithAster() {
    System.out.println("*" + string + "*");
  }
}
