package com.juny.observerpattern.bingogame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BingoGame {

  private List<Observer> observers = new ArrayList<>();
  private boolean hasWinner = false;
  private int playerCount, row, col, maxNumber;
  private int bingoNumber;

  public BingoGame () throws IOException {

    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      System.out.println(BingoMessage.INPUT_PLAYER_CHECK);
      playerCount = Integer.parseInt(br.readLine());

      System.out.println(BingoMessage.INPUT_ROW_CHECK);
      row = Integer.parseInt(br.readLine());

      System.out.println(BingoMessage.INPUT_COL_CHECK);
      col = Integer.parseInt(br.readLine());

      do {
        System.out.println(BingoMessage.INPUT_MAX_NUMBER_CHECK);
        maxNumber = Integer.parseInt(br.readLine());
        if (maxNumber < row * col) {
          System.out.println(BingoMessage.INPUT_MAX_NUMBER_ERROR);
        }
      } while (maxNumber < row * col);
    }
  }

  public void addObserver(Observer observer) {

    observers.add(observer);
  }

  public void notifyObservers() {

    for (var o : observers) {
      o.update(this);
    }
  }

  public void startGame() {

    while (!hasWinner) {
      notifyObservers();

    }
  }

  public int getBingoNumber() {
    return bingoNumber;
  }

  public int getMaxNumber() {
    return maxNumber;
  }

  public int getCol() {
    return col;
  }

  public int getRow() {
    return row;
  }

  public int getPlayerCount() {
    return playerCount;
  }

  public boolean isHasWinner() {
    return hasWinner;
  }

  public List<Observer> getObservers() {
    return observers;
  }

  public void setBingNumber(int num) {
    this.bingoNumber = num;
  }

  public void setHasWinner(boolean hasWinner) {
    this.hasWinner = hasWinner;
  }
}
