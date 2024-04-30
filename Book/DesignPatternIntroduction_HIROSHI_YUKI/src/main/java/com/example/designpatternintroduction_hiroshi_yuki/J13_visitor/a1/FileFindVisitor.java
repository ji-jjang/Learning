package com.example.designpatternintroduction_hiroshi_yuki.J13_visitor.a1;

import java.util.ArrayList;
import java.util.List;

public class FileFindVisitor extends Visitor {
  private String fileType;
  private List<File> foundFiles = new ArrayList<>();

  public FileFindVisitor(String fileType) {
    this.fileType = fileType;
  }

  public Iterable<File> getFoundFiles() {
    return foundFiles;
  }
  @Override
  public void visit(File file) {
    if (file.getName().endsWith(fileType)) {
      foundFiles.add(file);
    }
  }

  @Override
  public void visit(Directory directory) {
    for (var e : directory) {
      e.accept(this);
    }
  }
}
