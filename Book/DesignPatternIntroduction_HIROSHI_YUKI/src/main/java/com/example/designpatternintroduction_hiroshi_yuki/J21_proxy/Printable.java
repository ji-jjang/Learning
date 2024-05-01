package com.example.designpatternintroduction_hiroshi_yuki.J21_proxy;

public interface Printable {
  public abstract void setPrinterName(String name);

  public abstract String getPrinterName();

  public abstract void print(String string);
}
