package com.example.designpatternintroduction_hiroshi_yuki.J08_abstract_factory.a2.listfactory;

import com.example.designpatternintroduction_hiroshi_yuki.J08_abstract_factory.a2.factory.Factory;
import com.example.designpatternintroduction_hiroshi_yuki.J08_abstract_factory.a2.factory.Link;
import com.example.designpatternintroduction_hiroshi_yuki.J08_abstract_factory.a2.factory.Page;
import com.example.designpatternintroduction_hiroshi_yuki.J08_abstract_factory.a2.factory.Tray;

public class ListFactory extends Factory {
  @Override
  public Link createLink(String caption, String url) {
    return new ListLink(caption, url);
  }

  @Override
  public Tray createTray(String caption) {
    return new ListTray(caption);
  }

  @Override
  public Page createPage(String title, String author) {
    return new ListPage(title, author);
  }
}
