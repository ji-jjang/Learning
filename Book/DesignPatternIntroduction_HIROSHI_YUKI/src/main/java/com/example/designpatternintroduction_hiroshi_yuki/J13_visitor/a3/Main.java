package com.example.designpatternintroduction_hiroshi_yuki.J13_visitor.a3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: java Main dirname");
      System.out.println("Example: java Main .");
      System.exit(0);
    }

    String dirname = args[0];

    try {
      MyFileVisitor visitor = new MyFileVisitor();
      Path root = Path.of(dirname);
      Files.walkFileTree(root, visitor);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

