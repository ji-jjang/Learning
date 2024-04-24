package com.example.designpatternintroduction_hiroshi_yuki.J03_template_method.a4;

public class CharDisplay implements AbstractDisplay {
  private char ch;

  public CharDisplay(char ch) {
    this.ch = ch;
  }

  @Override
  public void open() {
    System.out.print("<<");
  }

  @Override
  public void print() {
    System.out.print(ch);
  }

  @Override
  public void close() {
    System.out.println(">>");
  }
}
