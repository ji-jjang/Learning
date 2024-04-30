package com.example.designpatternintroduction_hiroshi_yuki.J14_chain_of_responsibility.a3;

public class OddSupport extends Support {

  public OddSupport(String name) {
    super(name);
  }

  @Override
  protected boolean resolve(Trouble trouble) {
    if (trouble.getNumber() % 2 == 1) {
      return true;
    }
    return false;
  }
}
