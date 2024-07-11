package chapter8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 재귀 곱셈
public class Q5 {

  // 10
  // 20
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int a = Integer.parseInt(br.readLine());
    int b = Integer.parseInt(br.readLine());

    int ans = multiply(a, b);
    System.out.println("ans = " + ans);
  }

  public static int multiply(int a, int b) {

    if (b == 0) return 0;

    if (b == 1) return a;

    int half = multiply(a, b >> 1);

    if ((b & 1) == 0) return half + half;

    return half + half + a;
  }
}
