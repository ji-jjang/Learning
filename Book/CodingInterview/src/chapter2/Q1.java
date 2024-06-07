package chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

// Q. 정렬되어 있지 않은 연결리스트가 주어졌을 때 이 리스트에서 중복되는 원소를 제거하라. (임시 버퍼가 없다면?)
// input
// 9
// 1 2 3 3 3 4 5 6 7

// result
// 1 2 3 4 5 6 7
public class Q1 {

  // 임시 버퍼 사용할 수 있다면 O(N)
  public static void main2(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    List<Integer> list = new LinkedList<>();
    Set<Integer> set = new HashSet<>();

    int n = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    for(int i = 0; i < n; i++) {
      int num = Integer.parseInt(st.nextToken());
      set.add(num);
    }
    for (var e : set){
      list.add(e);
    }
    for (var e : list){
      System.out.println("e = " + e);
    }
  }

  // 임시 버퍼를 사용할 수 없다면, O(N^2)
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    List<Integer> list = new LinkedList<>();

    int n = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    for(int i = 0; i < n; i++) {
      list.add(Integer.parseInt(st.nextToken()));
    }

    for(int i = 0; i < list.size(); i++) {
      int cur = list.get(i);
      for(int j = i + 1; j < list.size(); j++) {
        if (list.get(j) == cur) {
          list.remove(j--);
        }
      }
    }

    for (var e : list){
      System.out.println("e = " + e);
    }
  }
}
