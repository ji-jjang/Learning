package com.example.designpatternintroduction_hiroshi_yuki.J02_adapter.inherit;

public class Main {
  public static void main(String[] args) {
    Print pb = new PrintBanner("Hello");
    pb.printWeak();
    pb.printStrong();
  }
}
