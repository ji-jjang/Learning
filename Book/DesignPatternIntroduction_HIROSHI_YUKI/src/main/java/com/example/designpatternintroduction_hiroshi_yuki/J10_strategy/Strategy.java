package com.example.designpatternintroduction_hiroshi_yuki.J10_strategy;

public interface Strategy {
  public abstract Hand nextHand();

  public abstract void study(boolean win);
}
