package chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// Q. 단방향 연결리스트가 주어졌을 때, 뒤에서 K번째 원소를 구하라.
// 입력 (N, K순)
// 11 3
// 1 2 3 3 3 4 5 6 7 8 9

// 결과
// 7

// 입력 (N, K순)
// 11 4
// 1 2 3 3 3 4 5 6 7 8 9

// 결과
// 6

public class Q2 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    List<Integer> list = new LinkedList<>();

    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());

    for(int i = 0; i < n; i++) {
      list.add(Integer.parseInt(st.nextToken()));
    }

    System.out.println(list.get(n - k));
  }

  // n을 모른다면 n을 구한 다음에 list의 처음부터 n - k번까지 가면 된다.
}

