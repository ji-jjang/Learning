package com.example.designpatternintroduction_hiroshi_yuki.J08_abstract_factory.factory;

public abstract class Link extends Item {
  protected String url;

  public Link(String caption, String url) {
    super(caption);
    this.url = url;
  }
}
