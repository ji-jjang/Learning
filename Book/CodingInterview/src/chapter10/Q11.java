package chapter10;

import java.util.Arrays;

// Q11. peak, valley 번갈아가며, 어느것부터 시작해도 상관 없음. 인접한 정수보다 작거나 같을 때
public class Q11 {

  static int[] nums = {5, 3, 1, 2, 3};

  public static void main(String[] args){

    Arrays.sort(nums);
    for (var e: nums) {
      System.out.printf(e + " ");
    }
    System.out.println();

    // 1 2 3 3 5
    // 1) 1 3 2 3 5
    // 2) 1 3 2 5 3
    for (int i = 1; i < nums.length; i += 2) {
      int tmp = nums[i];
      nums[i] = nums[i + 1];
      nums[i + 1] = tmp;
    }
    for (var e: nums) {
      System.out.printf(e + " ");
    }
  }
}
