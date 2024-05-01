package com.example.designpatternintroduction_hiroshi_yuki.J20_flyweight.a1;

public class BigString {

  private BigChar[] bigchars;

  public BigString(String string) {
    initShared(string);
  }

  public BigString(String string, boolean shared) {
    if (shared) {
      initShared(string);
    } else {
      initUnshared(string);
    }
  }
  public void initShared(String string) {
    BigCharFactory factory = BigCharFactory.getInstance();
    bigchars = new BigChar[string.length()];
    for (int i = 0; i < bigchars.length; i++) {
      bigchars[i] = factory.getBigChar(string.charAt(i));
    }
  }

  public void initUnshared(String string) {
    bigchars = new BigChar[string.length()];
    for (int i = 0; i < bigchars.length; i++) {
      bigchars[i] = new BigChar(string.charAt(i));
    }
  }

  public void print() {
    for (BigChar bc : bigchars) {
      bc.print();
    }
  }
}
