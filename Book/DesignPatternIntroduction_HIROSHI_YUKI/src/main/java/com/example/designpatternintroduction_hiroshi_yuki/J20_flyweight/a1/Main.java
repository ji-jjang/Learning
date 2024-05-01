package com.example.designpatternintroduction_hiroshi_yuki.J20_flyweight.a1;

public class Main {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Usage: java Main digis");
      System.out.println("Example: java Main 1212123");
      System.exit(0);
    }

    BigString bs;
    bs = new BigString(args[0], false);
    bs.print();
    bs = new BigString(args[0], true);
    bs.print();
  }
}
