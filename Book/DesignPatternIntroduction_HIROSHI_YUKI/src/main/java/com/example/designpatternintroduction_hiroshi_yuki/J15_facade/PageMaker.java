package com.example.designpatternintroduction_hiroshi_yuki.J15_facade;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PageMaker {
  private PageMaker() {
  }

  public static void makeWelcomPage(String mailadder, String filename) {
    try {
      Properties mailprop = Database.getProperties("maildata");
      String username = mailprop.getProperty(mailadder);
      HtmlWriter writer = new HtmlWriter(new FileWriter(filename));
      writer.title(username + "'s web page");
      writer.paragraph("Welcom to " + username + "'s web page!");
      writer.paragraph("Nice to meet you!");
      writer.mailto(mailadder, username);
      writer.close();
      System.out.println(filename + " is created for " + mailadder + " (" + username + ")");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
