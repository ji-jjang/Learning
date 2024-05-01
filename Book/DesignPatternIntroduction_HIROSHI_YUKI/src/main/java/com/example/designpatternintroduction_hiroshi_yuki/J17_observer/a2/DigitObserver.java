package com.example.designpatternintroduction_hiroshi_yuki.J17_observer.a2;

public class DigitObserver implements Observer {
  @Override
  public void update(NumberGenerator generator) {
    System.out.println("DigitObserver: " + generator.getNumber());
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
