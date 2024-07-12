package chapter8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q10 {
  // 화면 칠하기
//5
//2 2 10
//1 1 2 0 0
//1 1 2 0 0
//2 2 2 0 0
//3 3 3 3 3
//0 0 0 0 0

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    int [][]board = new int[n][n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    int r = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());
    int color = Integer.parseInt(st.nextToken());

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    dfs(r, c, board[r][c], color, board);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.printf(board[i][j] + " ");
      }
      System.out.println();
    }
  }

  private static void dfs(int r, int c, int oldColor, int newColor, int[][] board) {

    if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
      return;
    }

    if (board[r][c] == oldColor) {
      board[r][c] = newColor;
      dfs(r - 1, c, oldColor, newColor, board);
      dfs(r + 1, c, oldColor, newColor, board);
      dfs(r, c - 1, oldColor, newColor, board);
      dfs(r, c + 1, oldColor, newColor, board);
    }
  }
}
