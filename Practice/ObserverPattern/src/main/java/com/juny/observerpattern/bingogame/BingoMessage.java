package com.juny.observerpattern.bingogame;

public final class BingoMessage {

  public static String INPUT_PLAYER_CHECK = "Player 수:";
  public static String INPUT_ROW_CHECK = "행 수:";
  public static String INPUT_COL_CHECK = "열 수:";
  public static String INPUT_MAX_NUMBER_CHECK = "최대 숫자: ";
  public static String INPUT_MAX_NUMBER_ERROR = "최대 숫자는 행 * 열보다 커야 합니다.";
  private BingoMessage() {
  }
}
