package chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

// 입력
// N(3), M(3)
// 7 1 6 2 3 1

// 결과
// 1

// 입력
// N(3), M(3)
// 7 1 6 5 9 2

// 결과
// false

public class Q7 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    List<Integer> first = new LinkedList<>();
    List<Integer> second = new LinkedList<>();
    Set<Integer> set = new HashSet<>();

    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());

    for(int i = 0; i < n; i++) {
      first.add(Integer.parseInt(st.nextToken()));
      set.add(first.get(i));
    }
    boolean find = false;
    for(int j = 0; j < m; j++) {
      second.add(Integer.parseInt(st.nextToken()));
      if (set.contains(second.get(j))) {
        System.out.println(second.get(j));
        find = true;
      }
    }
    if (!find) {
      System.out.println(false);
    }
  }
}
