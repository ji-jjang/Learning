package com.example.designpatternintroduction_hiroshi_yuki.J06_prototype.a1.framework;

public abstract class Product implements Cloneable {
  public abstract void use(String s);

  public Product createCopy() {
    Product p = null;
    try {
      p = (Product)clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return p;
  }
}
