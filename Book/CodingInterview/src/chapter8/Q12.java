package chapter8;

// 8퀸
public class Q12 {

  static boolean[] col = new boolean[40];
  static boolean[] diag1 = new boolean[20];
  static boolean[] diag2 = new boolean[20];

  public static void main(String[] args) {

    int ans = dfs(0, 8, 0);
    System.out.println("ans = " + ans);
  }

  private static int dfs(int row, int n, int ans) {

    if (row == n) {
      return ans + 1;
    }
    for (int i = 0; i < n; i++) {
      if (col[i] || diag1[i + row] || diag2[row - i + n - 1])
        continue ;
      col[i] = true;
      diag1[i + row] = true;
      diag2[row - i + n - 1] = true;
      ans = dfs(row + 1, n, ans);
      col[i] = false;
      diag1[i + row] = false;
      diag2[row - i + n - 1] = false;
    }
    return ans;
  }
}
