package chapter4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

// Q. 이진 트리가 주어졌을 때 같은 깊이에 있는 노드를 연결리스트로 연결해주는 알고리즘을 작성하라.
public class Q3 {

  static class Node {
    int value;
    Node left;
    Node right;
    public Node(int value) {
      this.value = value;
    }
  }

  static void inOrder(Node node, ArrayList<LinkedList<Node>> list, int level) {
    if (Objects.isNull(node)) {
      return;
    }

    if (list.size() == level) {
      list.add(new LinkedList<>());
    }

    list.get(level).add(node);

    inOrder(node.left, list, level + 1);
    inOrder(node.right, list, level + 1);
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
    //        5
    //   2         7
    // 1   3     6   8
    //       4         9

    inOrder(node, list, 0);

    for (var e: list) {
      for (var n: e) {
        System.out.printf(" " + n.value);
      }
      System.out.println();
    }
  }
}
