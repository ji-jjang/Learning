package com.example.designpatternintroduction_hiroshi_yuki.J11_composite;

import lombok.ToString;

public abstract class Entry {

  public abstract String getName();

  // 크기를 얻는다
  public abstract int getSize();

  // 목록을 표시한다
  public void printList() {
    printList("");
  }

  // prefix를 앞에 붙여서 목록을 표시한다
  protected abstract void printList(String prefix);

  @Override
  public String toString() {
    return getName() + " (" + getSize() + ")";
  }
}
