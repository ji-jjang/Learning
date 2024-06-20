package chapter4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Q7. 순서 정하기 -> 위상 정렬
public class Q7 {
  public static void main(String[] args) {

    int n = 6;

    List<Integer>[] adj = new ArrayList[n + 1];
    for (int i = 0; i <= n; i++) {
      adj[i] = new ArrayList<>();
    }

    int[] deg = new int[n + 1];

    adj['a' - 'a'].add('d' - 'a');
    deg['d' - 'a']++;

    adj['f' - 'a'].add('b' - 'a');
    deg['b' - 'a']++;

    adj['b' - 'a'].add('d' - 'a');
    deg['d' - 'a']++;

    adj['f' - 'a'].add('a' - 'a');
    deg['a' - 'a']++;

    adj['d' - 'a'].add('c' - 'a');
    deg['c' - 'a']++;

    List<Integer> result = topologicalSort(adj, deg, n);

    if (result.size() != n) {
      System.out.println("Cycle exists");
    } else {
      for (int node : result) {
        System.out.print((char) (node + 'a') + " ");
      }
    }
  }

  public static List<Integer> topologicalSort(List<Integer>[] adj, int[] deg, int n) {
    Queue<Integer> q = new LinkedList<>();
    List<Integer> result = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      if (deg[i] == 0) {
        q.add(i);
      }
    }

    while (!q.isEmpty()) {
      int cur = q.poll();
      result.add(cur);

      for (int nxt : adj[cur]) {
        deg[nxt]--;
        if (deg[nxt] == 0) {
          q.add(nxt);
        }
      }
    }

    return result;
  }
}
