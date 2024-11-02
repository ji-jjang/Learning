package com.juny.observerpattern.bingogame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Referee implements Observer {

  private List<Integer> calledNumbers = new ArrayList<>();
  Random random = new Random();

  @Override
  public void update(BingoGame game) {

    int number = 0;
    do {
      number = random.nextInt(game.getMaxNumber() + 1);
    } while (calledNumbers.contains(number));

    System.out.println("call: " + number);
    game.setBingNumber(number);
    calledNumbers.add(number);
  }
}
