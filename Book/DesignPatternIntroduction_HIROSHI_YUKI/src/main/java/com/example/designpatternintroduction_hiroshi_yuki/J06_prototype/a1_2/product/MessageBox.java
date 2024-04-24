package com.example.designpatternintroduction_hiroshi_yuki.J06_prototype.a1_2.product;

import com.example.designpatternintroduction_hiroshi_yuki.J06_prototype.a1_2.framework.ConcreteProduct;

public class MessageBox extends ConcreteProduct {
  private char decochar;

  public MessageBox(char decochar) {
    this.decochar = decochar;
  }

  @Override
  public void use(String s) {
    int decolen = 1 + s.length() + 1;
    for (int i = 0; i < decolen; i++) {
      System.out.print(decochar);
    }
    System.out.println();
    System.out.println(decochar + s + decochar);
    for (int i = 0; i < decolen; i++) {
      System.out.print(decochar);
    }
    System.out.println();
  }

}
