package com.example.designpatternintroduction_hiroshi_yuki.J09_bridge.a2;

public class Main {
  public static void main(String[] args) {
    CountDisplay d = new CountDisplay(new FileDisplayImpl("star.txt"));
    d.multiDisplay(3);
  }
}
