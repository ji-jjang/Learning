package com.example.designpatternintroduction_hiroshi_yuki.J02_adapter.inherit;

public class PrintBanner extends Banner implements Print{

  public PrintBanner(String string) {
    super(string);
  }

  @Override
  public void printWeak() {
   showWithParen();
  }

  @Override
  public void printStrong() {
    showWithAster();
  }
}
