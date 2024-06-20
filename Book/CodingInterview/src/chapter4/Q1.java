package chapter4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Q. 방향 그래프, 두 노드 사이의 경로가 존재하는지
// bfs나 dfs로 탐색한다.
// a노드와 관련된 모든 노드를 탐색하면서 b노드가 나온다면 두 노드 사이의 경로가 존재한다고 볼 수 있다.
public class Q1 {
  static List<Integer>[] adj = new ArrayList[10];
  static boolean[] isVisited = new boolean[10];

  public boolean isConnected(int v1, int v2) {
    Queue<Integer> q = new LinkedList<>();
    q.add(v1);
    isVisited[v2] = true;
    while (!q.isEmpty()) {
      int cur = q.poll();
      for (int next : adj[cur]) {
        if (isVisited[next]) continue;
        if (next == v2){
          return true;
        }
        isVisited[next] = true;
        q.add(next);
      }
    }
    return false;
  }
}
