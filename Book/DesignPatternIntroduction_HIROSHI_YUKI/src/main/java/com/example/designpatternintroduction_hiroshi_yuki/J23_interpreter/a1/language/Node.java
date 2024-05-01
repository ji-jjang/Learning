package com.example.designpatternintroduction_hiroshi_yuki.J23_interpreter.a1.language;

public abstract class Node implements Executor {
  public abstract void parse(Context context) throws ParseException;
}
