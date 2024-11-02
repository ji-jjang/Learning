package com.juny.observerpattern.bingogame;

import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {

    BingoGame bingoGame = new BingoGame();

    for (int i = 0; i < bingoGame.getPlayerCount(); ++i) {
      Player player = new Player("player " + i, bingoGame);
      bingoGame.addObserver(player);
    }

    Referee referee = new Referee();
    bingoGame.addObserver(referee);

    WinnerChecker winnerChecker = new WinnerChecker();
    bingoGame.addObserver(winnerChecker);

    bingoGame.startGame();
  }
}
