package com.example.designpatternintroduction_hiroshi_yuki.J06_prototype.a2.product;

import com.example.designpatternintroduction_hiroshi_yuki.J06_prototype.a2.framework.Manager;
import com.example.designpatternintroduction_hiroshi_yuki.J06_prototype.a2.framework.Product;

public class Main {
  public static void main(String[] args) {
    Manager manager = new Manager();
    UnderlinePen upen = new UnderlinePen('-');
    MessageBox mbox = new MessageBox('*');
    MessageBox sbox = new MessageBox('/');

    manager.register("strong message", upen);
    manager.register("warning box", mbox);
    manager.register("slash box", sbox);

    Product p1 = manager.create("strong message");
    p1.use("Hello, world.");
    System.out.println();

    Product p2 = manager.create("warning box");
    p2.use("Hello, world.");
    System.out.println();

    Product p3 = manager.create("slash box");
    p3.use("Hello, world.");
    System.out.println();
  }
}
