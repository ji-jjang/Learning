package chapter10;

// Q. 정수 스트림, 주기적으로 어떤 수 x의 랭킹을 확인(x보다 같거나 작은 수의 개수 -> 작은 수의 개수 아닌가)

import java.util.TreeMap;

// 5,1,4,4,5,9,7,13,3
// rank1: 0
// rank3: 1
// rank4: 3
public class Q10 {

  static int[] stream = {5, 1, 4, 4, 5, 9, 7, 13, 3};

  public static void main(String[] args){

    int target = 4;

    TreeMap<Integer, Integer> m = new TreeMap<>();

    for (var num : stream) {
      m.put(num, m.getOrDefault(num, 0) + 1);
    }

    int ans = findRank(target, m) - 1;
    System.out.println(ans);
  }

  private static int findRank(int target, TreeMap<Integer, Integer> m) {

    int rank = 0;
    for (var key : m.headMap(target, true).keySet()) {
      rank += m.get(key);
    }
    return rank;
  }
}
