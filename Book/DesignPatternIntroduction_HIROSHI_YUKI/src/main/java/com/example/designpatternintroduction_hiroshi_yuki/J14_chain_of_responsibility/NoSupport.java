package com.example.designpatternintroduction_hiroshi_yuki.J14_chain_of_responsibility;

public class NoSupport extends Support {
  public NoSupport(String name) {
    super(name);
  }

  @Override
  protected boolean resolve(Trouble trouble) {
    return false;
  }
}
