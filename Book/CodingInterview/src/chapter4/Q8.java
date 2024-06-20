package chapter4;

// Q. 첫 번째 공통 조상
public class Q8 {
  static class Node {
    int value;
    Node left;
    Node right;
    public Node(int value) {
      this.value = value;
    }
  }

  static Node commonAncestor(Node root, Node p, Node q) {
    if (!covers(root, p) || !covers(root, q)) {
      return null;
    }
    return ancestorHelper(root, p, q);
  }

  private static Node ancestorHelper(Node root, Node p, Node q) {
    if (root == null || root == p || root == q) {
      return root;
    }

    boolean pIsOnLeft = covers(root.left, p);
    boolean qIsOnLeft = covers(root.left, q);
    if (pIsOnLeft != qIsOnLeft) {
      return root;
    }
    Node childSide = pIsOnLeft ? root.left : root.right;
    return ancestorHelper(childSide, p, q);
  }

  private static boolean covers(Node root, Node p) {
    if (root == null) return false;
    if (root == p) return true;
    return covers(root.left, p) || covers(root.right, p);
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

    Node commonAncestor = commonAncestor(node, node.right.right, node.right.right.right);
    System.out.println("commonAncestor.value = " + commonAncestor.value);

    //        5
    //   2         7
    // 1   3     6   8
    //       4         9
    //                   10
  }
}
