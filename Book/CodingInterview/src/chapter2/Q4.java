package chapter2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// Q. 값 K가 주어졌을 때 K보다 작은 노드들을 K보다 크거나 같은 노드들보다 앞에 오도록 코드를 작성하라.
// 입력
// N(7) K(5)
// 3 5 8 5 10 2 1

// 출력
// 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
// 내 출력: 3 -> 2 -> 1 -> 5 -> 8 -> 5 > 10

public class Q4 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    List<Integer> list = new LinkedList<>();
    List<Integer> lessThanK = new LinkedList<>();

    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());

    for(int i = 0; i < n; i++) {
      list.add(Integer.parseInt(st.nextToken()));
    }

    Iterator<Integer> it = list.iterator();
    while (it.hasNext()){
      int num = it.next();
      if (num < k) {
        lessThanK.add(num);
        it.remove();
      }
    }

    lessThanK.addAll(list);
    for (var e : lessThanK){
      System.out.printf("%d ", e);
    }
  }
}
