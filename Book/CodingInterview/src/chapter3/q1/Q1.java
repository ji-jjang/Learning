package chapter3.q1;

import java.util.ArrayList;
import java.util.HashMap;

// Q. 한 개로 세 개: 배열 한개로 스택 세 개를 어떻게 구현할지 설명하라.

public class Q1<T> {

  private final int STACK_SIZE = 3;
  private int[] stack;
  private final HashMap<Integer, int[]> range = new HashMap<>();

  public Q1(Integer totalElement) {
    int numberOfElement = totalElement / STACK_SIZE;

    int index = 0;
    int start = 0;
    int end = numberOfElement - 1;
    while (start < STACK_SIZE) {
      range.put(start, new int[]{start, end});
      ++index;
      start = end + 1;
      end += numberOfElement;
    }
    if (end != totalElement) {
      range.get(index)[1] = totalElement;
    }
  }

  // 원소가 99개 라면 0 ~ 32, 33 ~ 65, 66 ~ 98
  // 원소가 100개 라면 0 ~ 32, 33 ~ 65, 66 ~ 99

  public static void main(String[] args){
    ArrayList<Integer> arrayList = new ArrayList<>(100);
    for(int i = 0; i < 100; i++) {
      arrayList.add(i + 1);
    }
    Q1<Integer> myStack = new Q1<>(arrayList.size());
  }
}
