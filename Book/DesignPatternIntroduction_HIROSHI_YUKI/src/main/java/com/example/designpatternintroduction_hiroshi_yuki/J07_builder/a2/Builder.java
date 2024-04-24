package com.example.designpatternintroduction_hiroshi_yuki.J07_builder.a2;

public abstract class Builder {
  public abstract void makeTitle(String title);

  public abstract void makeString(String string);

  public abstract void makeItems(String[] items);

  public abstract void close();
}
