package com.example.designpatternintroduction_hiroshi_yuki.J21_proxy;

public class Printer implements Printable {

  private String name;
  public Printer() {
    heabyJob("Printer 인스턴스 생성 중...");
  }

  public Printer(String name) {
    this.name = name;
    heabyJob("Printer 인스턴스(" + name + ") 생성 중...");
  }
  @Override
  public void setPrinterName(String name) {
    this.name = name;
  }

  @Override
  public String getPrinterName() {
    return name;
  }

  @Override
  public void print(String string) {
    System.out.println("=== " + name + " ===");
    System.out.println(string);
  }

  public void heabyJob(String msg) {
    System.out.print(msg);
    for (int i = 0; i < 5; i++) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    System.out.println("완료");
  }
}
