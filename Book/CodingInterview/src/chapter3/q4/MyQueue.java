package chapter3.q4;



import java.util.Stack;

// Q. 스택 두 개로 큐 하나를 구현하라.

// 차례로 입력: 1 2 3 4 5 6

// Q : {1 2 3 4 5 }
// stackA : {} 입력을 위한 스택
// stackB : {1, 2, 3, 4, 5}

public class MyQueue {

  private final Stack<Integer> stackA = new Stack<>();
  private final Stack<Integer> stackB = new Stack<>();

  public void push(int x) {
    stackA.add(x);
  }

  public void pop() throws NoSuchFieldException {
    if (stackB.isEmpty()){
      while (!stackA.isEmpty()){
        stackB.push(stackA.pop());
      }
    }
    if (stackB.isEmpty()){
      throw new NoSuchFieldException();
    }
    stackB.pop();
  }

  public Integer peek() throws NoSuchFieldException {
    if (stackB.isEmpty()){
      while (!stackA.isEmpty()){
        stackB.push(stackA.pop());
      }
    }
    if (stackB.isEmpty()){
      throw new NoSuchFieldException();
    }
    return stackB.peek();
  }

  public static void main(String[] args) throws NoSuchFieldException {
    MyQueue myQueue = new MyQueue();

    myQueue.push(1);
    myQueue.push(2);
    myQueue.push(3);
    // stackA: [3, 2, 1]
    // stackB: [ ]
    System.out.println("myQueue.stackA = " + myQueue.stackA);
    System.out.println("myQueue.stackB = " + myQueue.stackB);

    // 1
    System.out.println("myQueue.peek() = " + myQueue.peek());

    myQueue.pop();
    // stackA: [ ]
    // stackB: [2, 3]
    System.out.println("myQueue.stackA = " + myQueue.stackA);
    System.out.println("myQueue.stackB = " + myQueue.stackB);

    // 1
    System.out.println("myQueue.peek() = " + myQueue.peek());

    // stackA: [ ]
    // stackB: [3]
    myQueue.pop();
    System.out.println("myQueue.stackA = " + myQueue.stackA);
    System.out.println("myQueue.stackB = " + myQueue.stackB);

    // 3
    System.out.println("myQueue.peek() = " + myQueue.peek());
  }
}
