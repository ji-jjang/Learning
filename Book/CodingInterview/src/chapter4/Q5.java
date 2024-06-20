package chapter4;

import java.util.ArrayList;
import java.util.LinkedList;

// Q5. 주어진 이진 트리가 이진 탐색 트리인지 검증하는 함수를 작성하라.
public class Q5 {
  static class Node {
    int value;
    Node left;
    Node right;
    public Node(int value) {
      this.value = value;
    }
  }

  static boolean checkBst(Node node, Integer min, Integer max) {
    if (node == null) return true;

    if ((min != null && node.value <= min) || (max != null && node.value > max)) return false;

    if (!checkBst(node.left, min, node.value) || !checkBst(node.right, node.value, max)) return false;

    return true;
  }

  public static void main(String[] args){
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

    System.out.println("checkBst(node, null, null) = " + checkBst(node, null, null));
  }
}

