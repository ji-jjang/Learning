package com.example.designpatternintroduction_hiroshi_yuki.J10_strategy.a1;

import lombok.ToString;

@ToString
public class Player {
  private String name;
  private Strategy strategy;
  private int wincount;
  private int losecount;
  private int gamecount;

  public Player(String name, Strategy strategy) {
    this.name = name;
    this.strategy = strategy;
  }

  public Hand nextHand() {
    return strategy.nextHand();
  }

  public void win() {
    strategy.study(true);
    wincount++;
    gamecount++;
  }

  public void lose() {
    strategy.study(false);
    losecount++;
    gamecount++;
  }

  public void even() {
    gamecount++;
  }

}
