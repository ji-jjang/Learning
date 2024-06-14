package chapter3.q5;

import java.util.Stack;

// Q. 가장 작은 값이 위로 오도록 스택을 정렬하는 프로그램. 추가적으로 하나 정도의 스택은 사용해도 괜찮음. push, pop, peek, isEmpty

// 1. stack B에는 stack A와 새로들어온 수를 비교하여 큰 값부터 넣는다.
// 2. stack B를 스택 A로 옮겨넣는다. 이 과정에서 작은 수가 앞에 오게 된다.

public class MyStack {

  private final Stack<Integer> stackA = new Stack<>();
  private final Stack<Integer> stackB = new Stack<>();

  public void push(int data) {
    while (!stackA.empty() && stackA.peek() < data ) {
      stackB.push(stackA.pop());
    }
    stackB.push(data);
    while (!stackA.empty()) {
      stackB.push(stackA.pop());
    }
    System.out.println();
    while (!stackB.empty()) {
//      System.out.println("stackB.peek() = " + stackB.peek());
      stackA.push(stackB.pop());
    }
    System.out.println();
  }
  public Integer peek() {

    return stackA.peek();
  }

  public void pop() {

    stackA.pop();
  }

  public boolean isEmpty() {

    return stackA.isEmpty();
  }

  public static void main(String[] args){
    MyStack myStack = new MyStack();
    myStack.push(1);
    myStack.push(2);
    myStack.push(3);
    myStack.push(4);
    myStack.push(5);
    while (!myStack.isEmpty()){
      System.out.println("myStack.peek() = " + myStack.peek());
      myStack.pop();
    }

    myStack.push(3);
    myStack.push(5);
    myStack.push(2);
    myStack.push(1);
    myStack.push(4);
    while (!myStack.isEmpty()){
      System.out.println("myStack.peek() = " + myStack.peek());
      myStack.pop();
    }
  }
}
