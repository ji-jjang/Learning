package chapter10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 정렬된 배열 A, B가 주어진다. A의 끝에는 B를 전부 넣을 수 있을 만큼 충분한 여유 공간이 있다. B와 A를 정렬된 상태로 병합하는 메서드를 작성하라.
// 10 7
// 10 20 30 4 2 3 1 5 10 9
// 12 16 13 14 15 21 24
public class Q1 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] nums = br.readLine().split(" ");
    int n = Integer.parseInt(nums[0]);
    int m = Integer.parseInt(nums[1]);
    int[] a = new int[n + m];
    int[] b = new int[m];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; ++i) {
      a[i] = Integer.parseInt(st.nextToken());
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < m; ++i) {
      b[i] = Integer.parseInt(st.nextToken());
    }

    mergeAB(a, b, n, m);

    for (var e : a){
      System.out.println("e = " + e);
    }
  }

  private static void mergeAB(int[] a, int[] b, int n, int m) {

    for (int i = 0; i < m; ++i) {
      a[i + n] = b[i];
    }
    Arrays.sort(a);
  }
}
