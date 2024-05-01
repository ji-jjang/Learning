package com.example.designpatternintroduction_hiroshi_yuki.J19_state;

public interface State {
  public abstract void doClock(Context context, int hour);

  public abstract void doUse(Context context);

  public abstract void doAlarm(Context context);

  public abstract void doPhone(Context context);
}
