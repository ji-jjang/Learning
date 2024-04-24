package com.example.designpatternintroduction_hiroshi_yuki.J08_abstract_factory.a2.divfactory;

import com.example.designpatternintroduction_hiroshi_yuki.J08_abstract_factory.a2.factory.Link;

public class DivLink extends Link {
  public DivLink(String caption, String url) {
    super(caption, url);
  }

  @Override
  public String makeHTML() {
    return "<div class=\"LINK\"><a href=\"" + url + "\">" + caption + "</a></div>\n";
  }
}
