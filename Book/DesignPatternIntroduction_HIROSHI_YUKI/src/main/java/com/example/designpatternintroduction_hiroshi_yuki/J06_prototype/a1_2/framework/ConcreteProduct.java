package com.example.designpatternintroduction_hiroshi_yuki.J06_prototype.a1_2.framework;

import com.example.designpatternintroduction_hiroshi_yuki.J06_prototype.a1_2.framework.Product;

public abstract class ConcreteProduct implements Product {
  @Override
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
