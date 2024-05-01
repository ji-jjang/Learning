package com.example.designpatternintroduction_hiroshi_yuki.J19_state.a3;

public interface Context {
  public abstract void setColck(int hour);

  public abstract void changeState(State state);

  public abstract void callSecurityCenter(String msg);

  public abstract void recordLog(String msg);
}
