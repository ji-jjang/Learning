package com.example.designpatternintroduction_hiroshi_yuki.J13_visitor.a3;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class MyFileVisitor extends SimpleFileVisitor<Path> {
  @Override
  public FileVisitResult preVisitDirectory(Path dir,
                                           BasicFileAttributes attrs) throws IOException {
    System.out.println("dir: " + dir);
    return FileVisitResult.CONTINUE;
  }

  @Override
  public FileVisitResult visitFile(Path file,
                                   BasicFileAttributes attrs) throws IOException {
    System.out.println("file: " + file);
    return FileVisitResult.CONTINUE;
  }
}

