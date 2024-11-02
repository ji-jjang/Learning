package com.juny.observerpattern.bingogame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player implements Observer {

  private String name;
  private int[][] board;
  private boolean isBingo;

  public Player(String name, BingoGame game) {
    this.name = name;
    this.board = new int[game.getRow()][game.getCol()];
    this.isBingo = false;
    initBoard(game);
  }

  private void initBoard(BingoGame game) {
    List<Integer> nums = new ArrayList<>();
    for (int i = 1; i <= game.getMaxNumber(); ++i) nums.add(i);
    Collections.shuffle(nums);

    int index = 0;
    for (int i = 0; i < board.length; ++i) {
      for (int j = 0; j < board[0].length; ++j) {
        board[i][j] = nums.get(index++);
      }
    }
  }

  @Override
  public void update(BingoGame game) {

    markBoard(game.getBingoNumber());
    checkBingo();
  }

  private void markBoard(int number) {
    for (int i = 0; i < board.length; ++i) {
      for (int j = 0; j < board[0].length; ++j) {
        if (board[i][j] == number) board[i][j] = -1;
      }
    }
  }

  public void checkBingo() {

    for (int i = 0; i < board.length; ++i) {
      int cnt = 0;
      for (int j = 0; j < board[0].length; ++j) {
        if (board[i][j] == -1)
          ++cnt;
      }
      if (cnt == board[0].length) {
        isBingo = true;
        return;
      }
    }
  }

  public String getName() {
    return name;
  }

  public int[][] getBoard() {
    return board;
  }

  public boolean isBingo() {
    return isBingo;
  }
}
