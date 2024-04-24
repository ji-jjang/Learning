package com.example.designpatternintroduction_hiroshi_yuki.J09_bridge.a1;

import java.util.Random;

public class RandomCountDisplay extends CountDisplay {
  private Random random = new Random();


  public RandomCountDisplay(DisplayImpl impl) {
    super(impl);
  }

  public void randomDisplay(int times) {
    multiDisplay(random.nextInt(times));
  }
}
