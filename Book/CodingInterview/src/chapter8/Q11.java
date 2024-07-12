package chapter8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 동전
public class Q11 {

  // dp[i] = 1 -> i원을 만들 수 있는 경우의 수
  // dp[0] = 1 -> 0원을 아무 동전도 쓰지 않고 만들 수 있음
  // dp[1] = 1
  // dp[2] = 1
  // dp[3] = 1
  // dp[5] = 1+1+1+1+1, 5 -> 2
  // dp[10] = 10, 5+5, 1*5 + 5, 1+1+1+1+1+1+1+1+1+1 -> 4

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());

    int[] coins = {1, 5, 10, 25};

    int[] dp = new int[n + 4];
    dp[0] = 1;

    for (var coin : coins) {
      for (int i = coin; i <= n; i++) {
        dp[i] += dp[i - coin];
      }
    }
    for (int i = 0; i <= n; i++) {
      System.out.println(dp[i]);
    }

    System.out.println("dp[n] = " + dp[n]);
  }
}
