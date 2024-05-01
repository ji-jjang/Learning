package com.example.designpatternintroduction_hiroshi_yuki.J19_state;

public class Main {
  public static void main(String[] args) {
    SafeFrame frame = new SafeFrame("State Sample");
    while (true) {
      for (int hour = 0; hour < 24; ++hour) {
        frame.setColck(hour);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }
}
