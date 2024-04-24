package com.example.designpatternintroduction_hiroshi_yuki.J01_iterator;

import java.util.Iterator;

public class Main {
  public static void main(String[] args) {
    BookShelf bookShelf = new BookShelf(4);
    bookShelf.appendBook(new Book("Around the world in 80 Days"));
    bookShelf.appendBook(new Book("Bible"));
    bookShelf.appendBook(new Book("Cinderella"));
    bookShelf.appendBook(new Book("Daddy-Long-Legs"));
    bookShelf.appendBook(new Book("East of Eden"));
    bookShelf.appendBook(new Book("Frankenstein"));
    bookShelf.appendBook(new Book("Gulliver's Travels"));
    bookShelf.appendBook(new Book("Hamlet"));

    Iterator<Book> it = bookShelf.iterator();
    while (it.hasNext()) {
      Book book = it.next();
      System.out.println("book.getName() = " + book.getName());
    }
    System.out.println();

    for (var e : bookShelf) {
      System.out.println("e.getName() = " + e.getName());
    }
    System.out.println();
  }
}
