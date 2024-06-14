package chapter3.q3;

// Q3 접시 무더기: SefOfStacks는 여러 스택으로 굿성되어 있으며, 이전 스택이 지정된 용량을 초과하는 경우 새로운 스택 생성
// 추가문제: 특정한 하위 스택에 대해서 poop을 수행하는 popAt(int index)도 구현하라.

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Stack;

public class SetOfStacks {

  private int currentStack = 0;
  private int boundary = 3;
  private List<Stack<Integer>> stacks = new ArrayList<>();

  public SetOfStacks() {
    stacks.add(new Stack<>());
  }


  public void push(int data) {
    stacks.get(currentStack).add(data);
    if (stacks.get(currentStack).size() == boundary) {
      stacks.add(new Stack<>());
      ++currentStack;
    }
  }
  public void pop() {
    if (stacks.get(currentStack).isEmpty()) {
      --currentStack;
      if (currentStack < 0) {
        throw new NoSuchElementException();
      }
    }
//    System.out.println(" stacks.get(currentStack).peek() = " + stacks.get(currentStack).peek());
    stacks.get(currentStack).pop();
  }

  public void popAt(int index) {
    if (index < 0 || index > stacks.size() - 1 || stacks.get(currentStack).isEmpty()) {
      throw new NoSuchElementException();
    }
  }

  public static void main(String[] args){
    SetOfStacks setOfStacks = new SetOfStacks();

//    setOfStacks.popAt(1);

    setOfStacks.push(1);
    setOfStacks.push(2);
    setOfStacks.push(3);
    setOfStacks.push(4);
    setOfStacks.push(5);

//    for(int i = 0; i < 5; i++) {
//      setOfStacks.pop();
//    }
    for (Stack<Integer> stack : setOfStacks.stacks) {
      while (!stack.empty()) {
        System.out.println("stack.peek() = " + stack.peek());
        stack.pop();
      }
      System.out.println("next stack");
    }
  }
}
