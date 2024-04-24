package com.example.designpatternintroduction_hiroshi_yuki.J08_abstract_factory.a2.factory;

public abstract class Item {
  protected String caption;

  public Item(String caption) {
    this.caption = caption;
  }

  public abstract String makeHTML();
}
