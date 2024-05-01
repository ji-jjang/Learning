package com.example.designpatternintroduction_hiroshi_yuki.J21_proxy.a1;

public class Main {
  public static void main(String[] args) {
    Printable p = new PrinterProxy("alice", "com.example.designpatternintroduction_hiroshi_yuki.J21_proxy.a1.Printer");
    System.out.println("이름은 현재 " + p.getPrinterName() + "입니다.");
    p.setPrinterName("Bob");
    System.out.println("이름은 현재 " + p.getPrinterName() + "입니다.");
    p.print("Hello, world");
  }
}
