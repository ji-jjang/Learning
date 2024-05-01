package com.example.designpatternintroduction_hiroshi_yuki.J18_memento;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Gamer {
  private int money;
  private List<String> fruits = new ArrayList<>();
  private Random random = new Random();

  private static String[] fruitsName = {
      "사과", "포도", "바나나", "오렌지",
  };

  public Gamer(int money) {
    this.money = money;
  }

  public int getMoney() {
    return money;
  }

  public void bet() {
    int dice = random.nextInt(6) + 1;
    if (dice == 1) {
      money += 100;
      System.out.println("소지금이 증가했습니다.");
    } else if (dice == 2) {
      money /= 2;
      System.out.println("소지금이 절반으로 줄어들었습니다.");
    } else if (dice == 6) {
      String f = getFruit();
      System.out.println("과일(" + f + ")을 받았습니다.");
      fruits.add(f);
    } else {
      System.out.println("변동 사항이 없습니다.");
    }
  }

  public Memento createMemento() {
    Memento m = new Memento(money);
    for (var f : fruits) {
      if (f.startsWith("맛있는 ")) {
        m.addFruit(f);
      }
    }
    return m;
  }

  public void restoreMemento(Memento memento) {
    this.money = memento.getMoney();
    this.fruits = memento.getFruits();
  }

  private String getFruit() {
    String f = fruitsName[random.nextInt(fruitsName.length)];
    if (random.nextBoolean()) {
      return "맛있는 " + f;
    } else {
      return f;
    }
  }

  @Override
  public String toString() {
    return "[moeny = " + money + ", fruits = " + fruits + "]";
  }
}
