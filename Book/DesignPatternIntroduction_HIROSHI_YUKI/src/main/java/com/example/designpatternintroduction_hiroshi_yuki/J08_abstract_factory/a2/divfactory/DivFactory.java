package com.example.designpatternintroduction_hiroshi_yuki.J08_abstract_factory.a2.divfactory;

import com.example.designpatternintroduction_hiroshi_yuki.J08_abstract_factory.a2.factory.Factory;
import com.example.designpatternintroduction_hiroshi_yuki.J08_abstract_factory.a2.factory.Link;
import com.example.designpatternintroduction_hiroshi_yuki.J08_abstract_factory.a2.factory.Page;
import com.example.designpatternintroduction_hiroshi_yuki.J08_abstract_factory.a2.factory.Tray;

public class DivFactory extends Factory {
  @Override
  public Link createLink(String caption, String url) {
    return new DivLink(caption, url);
  }

  @Override
  public Tray createTray(String caption) {
    return new DivTray(caption);
  }

  @Override
  public Page createPage(String title, String author) {
    return new DivPage(title, author);
  }
}
