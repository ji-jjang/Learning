package com.example.designpatternintroduction_hiroshi_yuki.J18_memento;

import java.util.ArrayList;
import java.util.List;

public class Memento {
  private int money;
  private List<String> fruits;

  public int getMoney() {
    return money;
  }

  public Memento(int money) {
    this.money = money;
    this.fruits = new ArrayList<>();
  }

  void addFruit(String fruit) {
    fruits.add(fruit);
  }

  List<String> getFruits() {
    return new ArrayList<>(fruits);
  }
}
