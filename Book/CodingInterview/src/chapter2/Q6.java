package chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

// Q. 주어진 연결리스트가 회문인지 검사하라.

// 1. 입력
// N(7)
// 1 2 3 4 3 2 1

// 결과
// true

// 2. 입력
// N(6)
// 1 2 3 3 2 1

// 결과
// true

// 3.입력
// N(5)
// 1 2 3 4 5

// 결과
// false
public class Q6 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    List<Integer> list = new LinkedList<>();

    int n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());

    for(int i = 0; i < n; i++) {
      list.add(Integer.parseInt(st.nextToken()));
    }

    if (isPalindrome(list)) {
      System.out.println("true");
      return ;
    }
    System.out.println("false");
  }

  private static boolean isPalindrome(List<Integer> list) {
    ListIterator<Integer> left = list.listIterator();
    ListIterator<Integer> right = list.listIterator(list.size());

    while (left.nextIndex() < right.previousIndex()) {
      int leftElement = left.next();
      int rightElement = right.previous();

      if (leftElement != rightElement) {
        return false;
      }
    }

    return true;
  }
}
