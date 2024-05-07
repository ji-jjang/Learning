package com.juny.dddstart2.board;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Access(AccessType.FIELD)
@Getter
public class ArticleContent {
  private String content;
  private String contentType;

  protected ArticleContent() {}

  public ArticleContent(String content, String contentType) {
    this.content = content;
    this.contentType = contentType;
  }
}
