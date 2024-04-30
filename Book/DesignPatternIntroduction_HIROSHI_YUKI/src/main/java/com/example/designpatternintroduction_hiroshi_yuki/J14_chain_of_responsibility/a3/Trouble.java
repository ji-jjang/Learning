package com.example.designpatternintroduction_hiroshi_yuki.J14_chain_of_responsibility.a3;

public class Trouble {
  private int number;

  public Trouble(int number) {
    this.number = number;
  }

  public int getNumber() {
    return number;
  }

  @Override
  public String toString() {
    return "[Trouble " + number + "]";
  }
}
