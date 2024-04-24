package com.example.designpatternintroduction_hiroshi_yuki.J01_iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookShelf implements Iterable<Book> {

  private List<Book> books;

  public BookShelf(int capacity) {
    this.books = new ArrayList<>(capacity);
  }

  public Book getBookAt(int index) {
    return books.get(index);
  }

  public void appendBook(Book book) {
    books.add(book);
  }

  public int getLength() {
    return books.size();
  }

  @Override
  public Iterator<Book> iterator() {
    return new BookShelfIterator(this);
  }
}
