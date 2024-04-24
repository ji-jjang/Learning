package com.example.designpatternintroduction_hiroshi_yuki.J06_prototype.framework;

public interface Product extends Cloneable {
  public abstract void use(String s);
  public abstract Product createCopy();
}
