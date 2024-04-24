package com.example.designpatternintroduction_hiroshi_yuki.J09_bridge.a3;

public class Main {
  public static void main(String[] args) {
    IncreaseDisplay d1 = new IncreaseDisplay(new CharDisplayImpl('<', '*', '>'), 1);
    IncreaseDisplay d2 = new IncreaseDisplay(new CharDisplayImpl('|', '#', '-'), 2);

    d1.increaseDisplay(4);
    System.out.println();
    d2.increaseDisplay(6);
  }
}
