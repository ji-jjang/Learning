package com.core.engineerinformationprocessing.J2022_01;

public class Q17 {
  static void func1(Q17A m) {
    m.a *= 10;
  }

  static void func2(Q17A m) {
    m.a += m.b;
  }

  public static void main(String args[]) {

    Q17A m = new Q17A();

    m.a = 100;
    func1(m);
    m.b = m.a;
    func2(m);

    System.out.printf("%d", m.a);
  }
}
