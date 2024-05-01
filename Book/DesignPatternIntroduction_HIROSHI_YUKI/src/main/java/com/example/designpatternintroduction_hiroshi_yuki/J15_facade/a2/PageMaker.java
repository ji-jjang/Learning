package com.example.designpatternintroduction_hiroshi_yuki.J15_facade.a2;

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

  public static void makeLinkPage(String filename) {
    try {
      HtmlWriter writer = new HtmlWriter(new FileWriter(filename));
      writer.title("Link page");
      Properties mailprop = Database.getProperties("maildata");
      for (var e : mailprop.stringPropertyNames()) {
        String username = mailprop.getProperty(e, "(unknown");
        writer.mailto(e, username);
      }
      writer.close();
      System.out.println(filename + " is created.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
