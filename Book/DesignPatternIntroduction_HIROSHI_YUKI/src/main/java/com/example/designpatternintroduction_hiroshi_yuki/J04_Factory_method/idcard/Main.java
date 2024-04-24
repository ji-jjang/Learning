package com.example.designpatternintroduction_hiroshi_yuki.J04_Factory_method.idcard;

import com.example.designpatternintroduction_hiroshi_yuki.J04_Factory_method.framework.Factory;
import com.example.designpatternintroduction_hiroshi_yuki.J04_Factory_method.framework.Product;

public class Main {
  public static void main(String[] args) {
    Factory factory = new IDCardFactory();
    Product card1 = factory.create("jiny");
    Product card2 = factory.create("juny");
    Product card3 = factory.create("kiki");
    card1.use();
    card2.use();
    card3.use();
  }
}
