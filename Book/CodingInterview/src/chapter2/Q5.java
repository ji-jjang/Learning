package chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// Q. 연결리스트 숫자를 표현할 때 각 노드가 자릿수 하나를 가지는 방식으로 표현할 수 있다. 각 숫자는 역순 배열되어 있고, 첫 번째 자릿수가 리스트의 맨 앞에 위치.
//    숫자 두 개가 있을 때 이 두 수를 더하여 그 합을 연결리스트로 반환하는 함수를 반환하라

// 입력
// N(3), M(3)
// 7 1 6 5 9 2

// 결과
// 2 1 9

public class Q5 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    List<Integer> first = new LinkedList<>();
    List<Integer> second = new LinkedList<>();

    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());

    for(int i = 0; i < n; i++) {
      first.add(Integer.parseInt(st.nextToken()));
    }
    for(int j = 0; j < m; j++) {
      second.add(Integer.parseInt(st.nextToken()));
    }

    Iterator<Integer> it1 = first.iterator();
    int digit = 1;
    int ret = 0;
    while (it1.hasNext()){
      ret += it1.next() * digit;
      digit *= 10;
    }
    int sum = ret;

    Iterator<Integer> it2 = second.iterator();
    digit = 1;
    ret = 0;
    while (it2.hasNext()){
      ret += it2.next() * digit;
      digit *= 10;
    }

    List<Integer> result = new LinkedList<>();
    sum += ret;
    while (sum > 0) {
      result.add(sum % 10);
      sum /= 10;
    }
    for (var e : result) {
      System.out.printf("%d ", e);
    }
  }
}
