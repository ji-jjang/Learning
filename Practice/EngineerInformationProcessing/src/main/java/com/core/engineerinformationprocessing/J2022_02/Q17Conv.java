package com.core.engineerinformationprocessing.J2022_02;

public class Q17Conv {
  public Q17Conv(int a) {
    this.a = a;
  }

  int func() {
    int b = 1;

    for (int i = 1; i < a; i++) {
      b = a * i + b;
      System.out.println("b = " + b);
    }
    System.out.printf("%d\n", a + b);
    return a + b;
  }

  int a;
}
