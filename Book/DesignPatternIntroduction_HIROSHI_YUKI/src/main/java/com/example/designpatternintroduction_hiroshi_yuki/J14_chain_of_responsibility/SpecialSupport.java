package com.example.designpatternintroduction_hiroshi_yuki.J14_chain_of_responsibility;

public class SpecialSupport extends Support {
  private int number;

  public SpecialSupport(String name, int number) {
    super(name);
    this.number = number;
  }

  @Override
  protected boolean resolve(Trouble trouble) {
    if (trouble.getNumber() == number) {
      return true;
    }
    return false;
  }
}
