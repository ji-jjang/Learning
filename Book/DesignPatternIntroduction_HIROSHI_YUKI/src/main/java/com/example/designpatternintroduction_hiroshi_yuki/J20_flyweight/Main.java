package com.example.designpatternintroduction_hiroshi_yuki.J20_flyweight;

public class Main {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Usage: java Main digis");
      System.out.println("Example: java Main 1212123");
      System.exit(0);
    }
    BigString bs = new BigString(args[0]);
    bs.print();
  }
}
