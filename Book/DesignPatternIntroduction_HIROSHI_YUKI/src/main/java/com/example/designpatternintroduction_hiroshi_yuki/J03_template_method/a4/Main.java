package com.example.designpatternintroduction_hiroshi_yuki.J03_template_method.a4;


public class Main {
  public static void main(String[] args) {
    AbstractDisplay d1 = new CharDisplay('H');

    AbstractDisplay d2 = new StringDisplay("Hello, world.");

    d1.display();
    d2.display();
  }
}
