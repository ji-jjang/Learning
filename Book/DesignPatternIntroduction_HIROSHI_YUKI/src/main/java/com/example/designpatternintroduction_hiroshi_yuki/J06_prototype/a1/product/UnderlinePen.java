package com.example.designpatternintroduction_hiroshi_yuki.J06_prototype.a1.product;

import com.example.designpatternintroduction_hiroshi_yuki.J06_prototype.a1.framework.Product;

public class UnderlinePen extends Product {
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
