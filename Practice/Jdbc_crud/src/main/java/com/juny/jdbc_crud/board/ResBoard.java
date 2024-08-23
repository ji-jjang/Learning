package com.juny.jdbc_crud.board;

import java.sql.Timestamp;

public class ResBoard {
  private int id;
  private String title;
  private String content;
  private java.sql.Timestamp createdAt;

  public ResBoard(int id, String title, String content, Timestamp createdAt) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.createdAt = createdAt;
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }
}
