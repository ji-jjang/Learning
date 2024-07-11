package chapter8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Q3 {

// 배열의 길이가 20억 이라면?
// 5
// -2 -4 1 2 4

// 11
// -10 -5 2 2 2 3 4 7 9 12 13

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    String[] split = br.readLine().split(" ");
    ArrayList<Integer> list = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      list.add(Integer.parseInt(split[i]));
    }


    ArrayList<Integer> ans = new ArrayList<>();

    ans.add(findMagic2(0, n - 1, list));
    // Naive Solution
    //    for (int i = 0; i < n; i++) {
    //      if (list.get(i) == i) {
    //        ans.add(i);
    //      }
    //    }

    System.out.println("ans: " + ans);
  }

  private static Integer findMagic2(int st, int en, ArrayList<Integer> list) {
    if (st > en) {
      return Integer.MIN_VALUE;
    }
    int mid = (st + en) >>> 1;
    int midValue = list.get(mid);
    if (mid == midValue){
      return mid;
    }

    int left = Math.min(mid - 1, midValue);
    int leftValue = findMagic2(st, left, list);
    if (leftValue >= 0) {
      return leftValue;
    }

    int right = Math.max(mid + 1, midValue);
    int rightValue = findMagic2(right, en, list);
    return rightValue;
  }

  private static int findMagic(int st, int en, ArrayList<Integer> list) {
    if (st > en) {
      return Integer.MIN_VALUE;
    }
    int mid = (st + en) >>> 1;
    if (list.get(mid) == mid) {
      return mid;
    } else if (list.get(mid) < mid) {
      return findMagic(mid + 1, en, list);
    } else {
      return findMagic(st, mid - 1, list);
    }
  }
}
