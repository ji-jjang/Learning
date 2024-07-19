package chapter10;

// Q. 음이 아닌 정수 40억개로 이루어진 입력 파일, 이 파일에 없는 정수를 생성하는 알고리즘, 단 메모리는 1GB만 사용할 수 있음
// 만약 메모리가 10MB라면?

import java.util.BitSet;

// 2^32비트 필요
public class Q7 {

  public static void main(String[] args){
    BitSet bitSet = new BitSet(Integer.MAX_VALUE);
    bitSet.set(1);
    bitSet.set(2);

    for (int i = 0; i < 10; ++i) {
      if (!bitSet.get(i)) {
        System.out.println("i = " + i);
      }
    }
  }
}

