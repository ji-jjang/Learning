package com.example.designpatternintroduction_hiroshi_yuki.J12_decorator.a2;


public class Main {

  public static void main(String[] args) {
    MultiStringDisplay md = new MultiStringDisplay();

    md.add("hi!");
    md.add("good morning.");
    md.add("good night!");
    md.show();

    SideBorder d1 = new SideBorder(md, '#');
    d1.show();

    FullBorder d2 = new FullBorder(md);
    d2.show();
  }
}