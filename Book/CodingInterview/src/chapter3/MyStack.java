package chapter3;

import java.util.EmptyStackException;

public class MyStack<T> {

  private static class StackNode<T> {
    private T data;
    private StackNode next;
    public StackNode(T data) {
      this.data = data;
    }
  }

  private StackNode<T> top;

  public T pop() {
    if (top == null) {
      throw new EmptyStackException();
    }
    T data = top.data;
    top = top.next;
    return data;
  }

  public void push(T data) {
    StackNode stackNode = new StackNode(data);
    stackNode.next = top;
    top = stackNode;
  }

  public T peek() {
    if (top == null) {
      throw new EmptyStackException();
    }
    return top.data;
  }

  public boolean isEmpty() {
    return top == null;
  }

  public static void main(String[] args){
    MyStack<Integer> myStack = new MyStack<>();
    myStack.push(1);
    myStack.push(2);
    myStack.push(3);
    while (!myStack.isEmpty()) {
      System.out.println("myStack.top = " + myStack.top.data);
      myStack.pop();
    }
  }
}
