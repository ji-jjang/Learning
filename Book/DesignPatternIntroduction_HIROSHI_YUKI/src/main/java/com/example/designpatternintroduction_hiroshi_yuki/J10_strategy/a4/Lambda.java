package com.example.designpatternintroduction_hiroshi_yuki.J10_strategy.a4;

import java.util.Arrays;
import java.util.List;

public class Lambda {
  public static void main(String[] args) {
    List<String> list = Arrays.asList("D", "B", "C", "E", "A");

    // 사전순으로 작은 순서
    list.sort((a, b) -> a.compareTo(b));
    System.out.println(list);

    // 사전식 순으로 큰 순서
    list.sort((a, b) -> b.compareTo(a));
    System.out.println(list);
  }
}
