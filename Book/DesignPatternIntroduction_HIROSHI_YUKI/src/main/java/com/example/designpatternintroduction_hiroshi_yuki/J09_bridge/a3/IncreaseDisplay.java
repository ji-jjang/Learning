package com.example.designpatternintroduction_hiroshi_yuki.J09_bridge.a3;

public class IncreaseDisplay extends CountDisplay {
  private int step;

  public IncreaseDisplay(DisplayImpl impl, int step) {
    super(impl);
    this.step = step;
  }

  public void increaseDisplay(int level) {
    int count = 0;
    for (int i = 0; i < level; i++) {
      multiDisplay(count);
      count += step;
    }
  }
}
