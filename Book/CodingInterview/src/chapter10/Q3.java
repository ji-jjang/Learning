package chapter10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Q. n개의 정수로 구성된 정렬 상태 배열을 임의의 횟수만큼 회전시켜 얻은 배열이 입력으로 주어진다. 이 배열에서 특정한 원소를 찾는 코드를 작성하라. 회전시키기 전 배열은 오름차순 정렬.
// 5
// {15,16,19,20,25,1,3,4,5,7,10,14}

// 3
// {2,2,2,3,4,2}
public class Q3 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int x = Integer.parseInt(br.readLine());
    String str = br.readLine();
    String[] numbers = str.substring(1, str.length() - 1).split(",");
    int[] nums = new int[numbers.length];
    for (int i = 0; i < numbers.length; i++) {
      nums[i] = Integer.parseInt(numbers[i]);
    }

    int ans = search(nums, x, 0, numbers.length - 1);
    System.out.println("ans = " + ans);
  }

  private static int search(int[] nums, int x, int left, int right) {
    int mid = (left + right) >>> 1;
    if (x == nums[mid]){
      return mid;
    }
    if (left > right) return -1;

    if (nums[left] < nums[mid]) {
      if (x >= nums[left] && x < nums[mid]) {
        return search(nums, x, mid - 1, right);
      } else {
        return search(nums, x, mid + 1, right);
      }
    } else if (nums[left] > nums[mid]) {
      if (x >= nums[mid] && x <= nums[right]) {
        return search(nums, x, mid + 1, right);
      } else {
        return search(nums, x, mid - 1, right);
      }
    } else if (nums[left] == nums[mid]) {
      if (nums[mid] != nums[right]) {
        return search(nums, x, mid + 1, right);
      } else {
        int ret = search(nums, x, mid - 1, right);
        if (ret == -1) {
          return search(nums, x, mid + 1, right);
        } else {
          return ret;
        }
      }
    }
    return -1;
  }
}
