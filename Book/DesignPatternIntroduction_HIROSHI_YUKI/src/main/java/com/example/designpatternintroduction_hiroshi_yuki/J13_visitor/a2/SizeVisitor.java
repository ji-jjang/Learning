package com.example.designpatternintroduction_hiroshi_yuki.J13_visitor.a2;

public class SizeVisitor extends Visitor {
  private int size = 0;

  public int getSize() {
    return size;
  }

  @Override
  public void visit(File file) {
    size += file.getSize();
  }

  @Override
  public void visit(Directory directory) {
    for (var e : directory) {
      e.accept(this);
    }
  }
}
