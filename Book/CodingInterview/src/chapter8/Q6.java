package chapter8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// Q 하노이의 탑

// 3

// 3
// Move disk 1 from A to C
// X Move disk 2 from A to B
// Move disk 1 from C to B
// X Move disk 3 from A to C
// Move disk 1 from B to A
// X Move disk 2 from B to C
// Move disk 1 from A to C

public class Q6 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    Stack<Integer> first = new Stack<>();
    Stack<Integer> second = new Stack<>();
    Stack<Integer> third = new Stack<>();

    for (int i = n; i >= 1; --i) first.push(i);

    hanoi(first, third, second, n, "A", "C", "B");
  }

  // argu1: from -> argu2: to
  private static void hanoi(Stack<Integer> first, Stack<Integer> third, Stack<Integer> second, int n,
    String from, String to, String temp) {
    if (n == 1) {
      int disk = first.pop();
      third.push(disk);
      System.out.println("Move disk " + disk + " from " + from + " to " + to);
      return;
    }

    hanoi(first, second, third, n - 1, from, temp, to);
    int disk = first.pop();
    third.push(disk);
    System.out.println("X Move disk " + disk + " from " + from + " to " + to);
    hanoi(second, third, first, n - 1, temp, to, from);
  }

  private static void hanoi(int first, int third, int n) {
    if (n == 1) {
      System.out.println(first + " " + third);
      return;
    }
    hanoi(first, 6 - first - third, n - 1);
    System.out.println(first + " " + third);
    hanoi(6 - first - third, third, n - 1);
  }
}
