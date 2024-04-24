package com.example.designpatternintroduction_hiroshi_yuki.J06_prototype.a2.framework;

public interface Product {
  public abstract void use(String s);

  public abstract Product createCopy();
}
