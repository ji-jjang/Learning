package com.example.designpatternintroduction_hiroshi_yuki.J04_Factory_method.idcard;

import com.example.designpatternintroduction_hiroshi_yuki.J04_Factory_method.framework.Product;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class IDCard extends Product {

  private String owner;
  private int serial;

  IDCard(String owner, int serial) {
    System.out.println(owner + "의 카드를" + serial + "번으로 만듭니다.");
    this.serial = serial;
    this.owner = owner;
  }

  @Override
  public void use() {
    System.out.println(this + "을 사용합니다.");
  }
}
