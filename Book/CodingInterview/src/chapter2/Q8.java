package chapter2;

// Q. 순환 연결 리스트에서 순환 되는 부분의 첫째 노드를 반환하는 알고리즘을 반환하라.

// 1 -> 2 -> 3 -> 4 -> 5 -> 3

// 결과: 3

public class Q8 {

  public static class Node {

    public Integer value;
    public Node next;

    public Node (Integer value) {
      this.value = value;
      this.next = null;
    }
  }

  public static void main(String[] args){

    Node head = new Node(1);
    head.next = new Node(2);
    head.next.next = new Node(3);
    head.next.next.next = new Node(4);
    head.next.next.next.next = new Node(5);
    head.next.next.next.next.next = head.next.next;

    Node firstNode = findFirstNodeInCycle(head);
    System.out.println("firstNode.value = " + firstNode.value);
  }

  private static Node findFirstNodeInCycle(Node head) {
    Node slow = head;
    Node fast = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        break;
      }
    }

    slow = head;
    while (slow != fast) {
      slow = slow.next;
      fast = fast.next;
    }

    return slow;
  }
}
