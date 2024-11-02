package com.juny.observerpattern.bingogame;

import java.util.ArrayList;
import java.util.List;

public class WinnerChecker implements Observer {

  private List<String> winners = new ArrayList<>();

  @Override
  public void update(BingoGame game) {

    for (var observer : game.getObservers()) {
      if (observer instanceof Player) {
        if (((Player) observer).isBingo()) {
          winners.add(((Player) observer).getName());
        }
      }
    }
    if (winners.size() > 0) {
      game.setHasWinner(true);
      System.out.printf("우승자는 {");
      for (var e : winners) {
        System.out.printf(e + " ");
      }
      System.out.println("} 입니다.");
    }
  }
}
