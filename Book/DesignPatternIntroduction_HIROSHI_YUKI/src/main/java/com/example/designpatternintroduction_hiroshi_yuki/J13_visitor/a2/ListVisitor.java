package com.example.designpatternintroduction_hiroshi_yuki.J13_visitor.a2;

public class ListVisitor extends Visitor {

  private String currentdir = "";
  @Override
  public void visit(File file) {
    System.out.println(currentdir + "/" + file);
  }

  @Override
  public void visit(Directory directory) {
    System.out.println(currentdir + "/" + directory);
    String savedir = currentdir;
    currentdir = currentdir + "/" + directory.getName();
    for (var e : directory) {
      e.accept(this);
    }
    currentdir = savedir;
  }
}
