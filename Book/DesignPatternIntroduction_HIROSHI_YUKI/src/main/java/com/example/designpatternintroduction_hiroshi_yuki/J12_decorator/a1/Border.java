package com.example.designpatternintroduction_hiroshi_yuki.J12_decorator.a1;

public abstract class Border extends Display {
  protected Display display;

  public Border(Display display) {
    this.display = display;
  }
}
