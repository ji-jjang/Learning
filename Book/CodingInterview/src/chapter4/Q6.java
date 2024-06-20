package chapter4;

// Q. 이진 탐색 트리에서 주어진 노드의 다음 노드(중위 후속자 노드)를 찾는 알고리즘을 작성하라.

// 중위: 왼쪽 -> 현재 -> 오른쪽

public class Q6 {

  static class Node {
    int value;
    Node left;
    Node right;
    Node parent;
    public Node(int value) {
      this.value = value;
    }
  }

  static Node inorderSuccessor(Node node) {
    if (node == null) return null;

    if (node.right != null) {
      return leftMostChild(node.right);
    } else {
      Node q = node;
      Node x = q.parent;
      while (x != null && x.left != q) {
        q = x;
        x = x.parent;
      }
      return x;
    }
  }

  private static Node leftMostChild(Node node) {
    if (node == null) return null;

    while (node.left != null) {
      node = node.left;
    }
    return node;
  }

  public static void main(String[] args){
    Node node = new Node(5);
    node.left = new Node(2);
    node.right = new Node(7);
    node.left.parent = node;
    node.right.parent = node;

    node.left.left = new Node(1);
    node.left.right = new Node(3);
    node.left.left.parent = node.left;
    node.left.right.parent = node.left;

    node.right.left = new Node(6);
    node.right.right = new Node(8);
    node.right.left.parent = node.right;
    node.right.right.parent = node.right;

    node.left.right.right = new Node(4);
    node.left.right.right.parent = node.left.right;

    node.right.right.right = new Node(9);
    node.right.right.right.parent = node.right.right;

    //        5
    //   2         7
    // 1   3     6   8
    //       4         9

    Node target = node.right.right.right;

    Node successor = inorderSuccessor(target);
    if (successor != null) {
      System.out.println("Inorder Successor of " + target.value + " is " + successor.value);
    } else {
      System.out.println("Inorder Successor of " + target.value + " does not exist");
    }
  }
}
