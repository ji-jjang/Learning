package com.example.designpatternintroduction_hiroshi_yuki.J07_builder.a3;

public abstract class Builder {
  public abstract void makeTitle(String title);

  public abstract void makeString(String str);

  public abstract void makeItems(String[] items);

  public abstract void close();
}
