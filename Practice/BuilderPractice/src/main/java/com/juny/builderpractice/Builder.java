package com.juny.builderpractice;

public interface Builder {
  public void makeTitle(String title);

  public void makeString(String string);

  public void makeItems(String[] items);

  public void close();
}
