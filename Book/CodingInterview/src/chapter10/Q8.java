package chapter10;

// Q. 1부터 N(32,000)까지 숫자로 이루어진 배열, 배열엔 중복된 숫자가 나타날 수 있고, N이 무엇인지 알 수 없다. 사용가능한 메모리가 4KB일 때, 중복된 원소를 모두 출력하려면?

import java.util.BitSet;

public class Q8 {

  // 사용한 메모리 약 8KB
  public static void main(String[] args){

    int[] arr = {1, 2, 3, 1, 5, 6, 2, 3, 7, 8, 9, 10, 6};

    int N = 32000;
    BitSet bitSet = new BitSet(N);
    BitSet dup = new BitSet(N);

    for (int num : arr) {
      if (bitSet.get(num)) {
        dup.set(num);
      } else {
        bitSet.set(num);
      }
    }

    for (int i = 1; i <= N; i++) {
      if (dup.get(i)) {
        System.out.println("i = " + i);
      }
    }
  }
}
