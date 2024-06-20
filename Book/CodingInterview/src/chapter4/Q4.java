package chapter4;

import java.util.ArrayList;
import java.util.LinkedList;

// Q4. 이진 트리가 균형 잡혀있는지 확인하는 함수를 작성하라.

// 재귀 너무 신기하다..
public class Q4 {

  static class Node {
    int value;
    Node left;
    Node right;
    public Node(int value) {
      this.value = value;
    }
  }

  static boolean isBalanced(Node root) {
    return checkHeight(root) != -1;
  }

  static int count = 0;
  static int checkHeight(Node node) {

    System.out.println("count = " + count++);
    if (node == null) {
      return 0;
    }

    int leftHeight = checkHeight(node.left);
    if (leftHeight == -1) return -1;

    int rightHeight = checkHeight(node.right);
    if (rightHeight == -1) return -1;

    System.out.println("leftHeight = " + leftHeight);
    System.out.println("rightHeight = " + rightHeight);
    if (Math.abs(leftHeight - rightHeight) > 1) {
      return -1;
    }

    return Math.max(leftHeight, rightHeight) + 1;
  }

  public static void main(String[] args){
    ArrayList<LinkedList<Node>> list = new ArrayList<>();

    Node node = new Node(5);
    node.left = new Node(2);
    node.left.left = new Node(1);
    node.left.right = new Node(3);
    node.left.right.right = new Node(4);

    node.right = new Node(7);
    node.right.left = new Node(6);
    node.right.right = new Node(8);
    node.right.right.right = new Node(9);
    node.right.right.right.right = new Node(10);
    //        5
    //   2         7
    // 1   3     6   8
    //       4         9
    //                   10
    node.right.right.right = new Node(9);
    boolean balanced = isBalanced(node);
    System.out.println("balanced = " + balanced);
  }
}
