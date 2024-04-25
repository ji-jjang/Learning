package com.example.designpatternintroduction_hiroshi_yuki.J10_strategy.a1;

import lombok.ToString;

@ToString
public enum Hand {
  ROCK("바위", 0),
  SCISSORS("가위", 1),
  PAPER("보", 2);

  private String name;
  private int handValue;

  private static Hand[] hands = {
      ROCK, SCISSORS, PAPER
  };

  Hand(String name, int handValue) {
    this.name = name;
    this.handValue = handValue;
  }

  public static Hand getHand(int handValue) {
    return hands[handValue];
  }

  public boolean isStrongerThan(Hand hand) {
    return fight(hand) == 1;
  }

  public boolean isWeakerThan(Hand hand) {
    return fight(hand) == -1;
  }

  private int fight(Hand h) {
    if (this == h) {
      return 0;
    } else if ((this.handValue + 1) % 3 == h.handValue) {
      return 1;
    } else {
      return -1;
    }
  }
}
