package com.example.designpatternintroduction_hiroshi_yuki.J07_builder.a1;

public interface Builder {
  public void makeTitle(String title);

  public void makeString(String string);

  public void makeItems(String[] items);

  public void close();
}
