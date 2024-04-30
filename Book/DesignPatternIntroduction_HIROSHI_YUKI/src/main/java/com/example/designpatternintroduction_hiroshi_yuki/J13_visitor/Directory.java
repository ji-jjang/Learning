package com.example.designpatternintroduction_hiroshi_yuki.J13_visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Directory extends Entry implements Iterable<Entry> {
  private String name;
  private List<Entry> directory = new ArrayList<>();

  public Directory(String name) {
    this.name = name;
  }

  @Override
  public void accept(Visitor v) {
    v.visit(this);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getSize() {
    int size = 0;
    for (var e : directory) {
      size += e.getSize();
    }
    return size;
  }

  @Override
  public Iterator<Entry> iterator() {
    return directory.iterator();
  }

  public Entry add(Entry entry) {
    directory.add(entry);
    return this;
  }
}
