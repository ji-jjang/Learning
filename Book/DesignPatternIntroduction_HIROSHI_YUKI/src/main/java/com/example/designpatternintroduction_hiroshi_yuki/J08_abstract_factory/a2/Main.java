package com.example.designpatternintroduction_hiroshi_yuki.J08_abstract_factory.a2;

// list.html com.example.designpatternintroduction_hiroshi_yuki.J08_abstract_factory.listfactory.ListFactory
// div.html com.example.designpatternintroduction_hiroshi_yuki.J08_abstract_factory.a2.divfactory.DivFactory


import com.example.designpatternintroduction_hiroshi_yuki.J08_abstract_factory.a2.factory.Factory;
import com.example.designpatternintroduction_hiroshi_yuki.J08_abstract_factory.a2.factory.Page;

public class Main {
  public static void main(String[] args) {
    System.out.println("args = " + args[0] + " " + args[1]);
    if (args.length != 2) {
      System.out.println("args = " + args);
      System.out.println("Usage: java Main filename.html class.name.of.ConcreteFactory");
      System.out.println("Example 1: java Main list.html listfactory.ListFactory");
      System.out.println("Example 2: java Main div.html divfactory.DivFactory");
      System.exit(0);
    }

    String filename = args[0];
    String classname = args[1];


    Factory factory = Factory.getFactory(classname);

    Page page = factory.createNaverPage();

    page.output(filename);
  }
}
