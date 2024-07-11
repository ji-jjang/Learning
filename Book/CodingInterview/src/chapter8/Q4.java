package chapter8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// Q4. 어떤 집합의 부분집합 전부 구하라
public class Q4 {
  // 5
  // 1 2 3 4 5
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());

    List<Integer> list = new ArrayList<>();
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; ++i) {
      list.add(Integer.parseInt(st.nextToken()));
    }

    List<List<Integer>> subsets = new ArrayList<>();
    dfs(0, list.size(), new ArrayList<>(), list, subsets);

    for (var e : subsets) {
      System.out.println(e);
    }
    System.out.println("subsets.size() = " + subsets.size());
  }

  private static void dfs(int depth, int size, ArrayList<Integer> cur, List<Integer> list, List<List<Integer>> subsets) {
    if (depth == size) {
      subsets.add(new ArrayList<>(cur));
      return;
    }
    cur.add(list.get(depth));
    dfs(depth + 1, size, cur, list, subsets);

    cur.remove(cur.size() - 1);
    dfs(depth + 1, size, cur, list, subsets);
  }
}
