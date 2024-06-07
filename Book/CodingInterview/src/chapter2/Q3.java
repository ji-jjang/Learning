package chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// Q. 단방향 연결 리스트가 주어졌을 때, 중간에 있는 노드를 하나 삭제하는 알고리즘을 구현하라. 단, 삭제한 노드에만 접근할 수 있다.

// cur->prev->next = cur->next (이렇게 포인터만 변경해주면 될 듯?)

// 입력 (N, K순)
// 11 4
// 1 2 3 3 3 4 5 6 7 8 9

// 결과
// 1 2 3 3 4 5 6 7 8 9

public class Q3 {

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

    Integer randomCenterNode = list.get(k);
    list.remove(randomCenterNode);

    for (var e : list){
      System.out.println("e = " + e);
    }
  }
}
