package chapter4;

import java.util.Objects;

// Q. 최소 트리: 오름차순으로 정렬된 배열, 원소가 정수이며 중복된 값이 없을 때 높이가 최소가 되는 이진 탐색 트리를 만드는 알고리즘

// 노드를 절반씩 자르면 두 서브트리의 높이가 균등하게 나누어짐. 이를 반복하면 최소 높이.
public class Q2 {

  static class Node {
    int value;
    Node left;
    Node right;
    public Node(int value) {
      this.value = value;
    }
  }

  static Node createMinimumHeightTree(int[] nums, int left, int right) {
    if (left > right) {
      return null;
    }

    int mid = (left + right) >>> 1;
    Node node = new Node(nums[mid]);

    node.left = createMinimumHeightTree(nums, left, mid - 1);
    node.right = createMinimumHeightTree(nums, mid + 1, right);

    return node;
  }

  static void inOrder(Node node) {
    if (Objects.isNull(node)) {
      return;
    }
    inOrder(node.left);
    System.out.print(node.value + " ");
    inOrder(node.right);
  }


  public static void main(String[] args){
    int[] nums = {1,2,3,4,5,6,7,8,9};
    Node node = createMinimumHeightTree(nums, 0, nums.length - 1);
    inOrder(node);
  }
  //        5
  //   2         7
  // 1   3     6   8
  //       4         9
}
