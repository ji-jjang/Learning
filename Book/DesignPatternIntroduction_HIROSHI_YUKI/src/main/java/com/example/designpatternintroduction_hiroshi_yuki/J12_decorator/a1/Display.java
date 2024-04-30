package com.example.designpatternintroduction_hiroshi_yuki.J12_decorator.a1;

public abstract class Display {
  public abstract int getColumns();
  public abstract int getRows();
  public abstract String getRowText(int row);

  public void show() {
    for (int i = 0; i < getRows(); i++) {
      System.out.println(getRowText(i));
    }
  }
}