package chapter8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 행의 개수는 r, 열의 개수는 c 격자판 왼쪽 상단 꼭지점에 로봇.
// 로봇은 오른쪽 아니면 아래쪽으로만 이동할 수 있다.
// 어떤 셸은 금지 구역으로 있어서 로봇이 접근할 수 없을 때 왼쪽 상단 꼭짓점에서 오른쪽 하단 꼭짓점으로 경로를 찾는 알고리즘
public class Q2 {

//3 3
//0 0 0
//1 1 0
//0 0 0

//3 3
//0 0 0
//1 1 1
//0 0 0
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] split = br.readLine().split(" ");
    int row = Integer.parseInt(split[0]);
    int col = Integer.parseInt(split[1]);

    int[][] board = new int[row][col];
    for (int i = 0; i < row; ++i) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < col; ++j) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    ArrayList<int[]> ans = new ArrayList<>();
    boolean[][] isFailed = new boolean[row][col];

    findPath(0, 0, board, ans, isFailed);

    for (var e : ans) {
      System.out.println(e[0] + " " + e[1]);
    }
  }

  private static boolean findPath(int y, int x, int[][] board, ArrayList<int[]> ans, boolean[][] isFailed) {
    int r = board.length;
    int c = board[0].length;
    if (y >= r || x >= c || board[y][x] == 1 || isFailed[y][x]) return false;

    if ((y == r - 1 && x == c - 1) || findPath(y + 1, x, board, ans, isFailed) || findPath(y, x + 1, board, ans, isFailed)) {
      ans.add(0, new int[]{y, x});
      return true;
    }
    isFailed[y][x] = true;
    return false;
  }
}
