package com.example.designpatternintroduction_hiroshi_yuki.J05_singleton.a1;

public class TicketMaker {
  private int ticket = 1000;
  private static TicketMaker singleton = new TicketMaker();

  private TicketMaker() {
    System.out.println("TickerMaker 생성");
  }

  public static TicketMaker getInstance() {
    return singleton;
  }

  public synchronized int getNextTicketNumber() {
    return ticket++;
  }
}
