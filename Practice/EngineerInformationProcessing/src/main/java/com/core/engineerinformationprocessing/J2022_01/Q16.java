package com.core.engineerinformationprocessing.J2022_01;

public class Q16 {

  public static void main(String[] args) {
    Thread t1 = new Thread(new Q16Car());
    t1.start();
  }
}
