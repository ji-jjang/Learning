package com.example.designpatternintroduction_hiroshi_yuki.J06_prototype.a1_2.product;

import com.example.designpatternintroduction_hiroshi_yuki.J06_prototype.a1_2.framework.ConcreteProduct;

public class UnderlinePen extends ConcreteProduct {
  private char ulchar;

  public UnderlinePen(char ulchar) {
    this.ulchar = ulchar;
  }

  @Override
  public void use(String s) {
    int ulen = s.length();
    System.out.println(s);
    for (int i = 0; i < ulen; i++) {
      System.out.print(ulchar);
    }
    System.out.println();
  }
}
