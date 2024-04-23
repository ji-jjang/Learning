package com.core.engineerinformationprocessing.J2020_04;

public class Q17 {
  public static void main(String[] args) {
    int i = 0;
    int sum = 0;
    while (i < 10){
      i++;
      if (i % 2 == 1)
        continue;
      sum += i;
    }
    System.out.print(sum);
  }
}
