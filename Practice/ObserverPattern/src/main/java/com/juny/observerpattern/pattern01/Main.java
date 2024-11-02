package com.juny.observerpattern.pattern01;

public class Main {
  public static void main(String[] args){

    NumberGenerator generator = new IncrementalNumberGenerator(10, 50, 5);
    DigitObserver observer1 = new DigitObserver();
    GraphObserver observer2 = new GraphObserver();
    generator.addObserver(observer1);
    generator.addObserver(observer2);
    generator.execute();
  }
}
