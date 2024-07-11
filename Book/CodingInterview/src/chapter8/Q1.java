package chapter8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Q1. 어떤 아이가 n개의 계단을 오른다. 한 번에 1계단 오르기도 하고, 2계단이나 3계단을 오르기도 한다. 계단을 오르는 방법이 몇가지인지 계산하라.
public class Q1 {
  // 점화식 도출
  // dp[1] = {1}
  // dp[2] = {1, 1}, {2}
  // dp[3] = {1, 1, 1}, {1, 2}, {2, 1}, {3}
  // dp[4] = {1, 1, 1, 1}, {1, 2, 1}, {1, 1, 2}, {1, 3}, {2, 1, 1}, {2, 2}, {3, 1}
  // dp[5] = {1, 1, 1, 1, 1}, {1, 1, 1, 2}, {1, 1, 2, 1}, {1, 1, 3}, {1, 2, 1, 1}, {1, 2, 2}, {1, 3, 1}, {2, 1, 1, 1}, {2, 1, 2}, {2, 2, 1}, {2, 3}, {3, 1, 1}, {3, 2}
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());

    int[] dp = new int[100_004];
    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 4;
    for (int i = 4; i <= n; ++i) {
      dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
    }
    System.out.println(dp[n]);
  }
}
