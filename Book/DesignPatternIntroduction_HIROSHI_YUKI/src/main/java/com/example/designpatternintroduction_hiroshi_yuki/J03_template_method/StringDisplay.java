package com.example.designpatternintroduction_hiroshi_yuki.J03_template_method;

public class StringDisplay extends AbstractDisplay {
  private String string;
  private int width;

  public StringDisplay(String string) {
    this.string = string;
    this.width = string.length();
  }

  @Override
  public void open() {
    printLine();
  }

  @Override
  public void print() {
    System.out.println("|" + string + "|");
  }

  @Override
  public void close() {
    printLine();
  }

  private void printLine() {
    System.out.printf("+");
    for (int i = 0; i < width; ++i) {
      System.out.printf("-");
    }
    System.out.println("+");
  }
}
