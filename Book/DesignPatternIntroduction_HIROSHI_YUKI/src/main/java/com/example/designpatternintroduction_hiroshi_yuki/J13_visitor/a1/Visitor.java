package com.example.designpatternintroduction_hiroshi_yuki.J13_visitor.a1;

import lombok.ToString;

@ToString
public abstract class Visitor {
  public abstract void visit(File file);

  public abstract void visit(Directory directory);
}
