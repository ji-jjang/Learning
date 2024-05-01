package com.example.designpatternintroduction_hiroshi_yuki.J23_interpreter;

public abstract class Node {
  public abstract void parse(Context context) throws ParseException;
}
