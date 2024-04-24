package com.example.designpatternintroduction_hiroshi_yuki.J04_Factory_method.idcard;

import com.example.designpatternintroduction_hiroshi_yuki.J04_Factory_method.framework.Factory;
import com.example.designpatternintroduction_hiroshi_yuki.J04_Factory_method.framework.Product;

public class IDCardFactory extends Factory {
  private int serial = 100;

  @Override
  protected Product createProduct(String owner) {
    return new IDCard(owner, serial++);
  }

  @Override
  protected void registerProduct(Product product) {
    System.out.println(product + "을 등록했습니다.");
  }
}
