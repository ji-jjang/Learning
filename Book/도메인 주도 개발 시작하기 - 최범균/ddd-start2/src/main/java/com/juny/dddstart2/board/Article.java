package com.juny.dddstart2.board;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "article")
@SecondaryTable(
    name = "article_content",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "id")
)
@Getter
public class Article {
  @Id
  @GeneratedValue
  private Long id;

  private String title;

  @AttributeOverrides({
    @AttributeOverride(
        name = "content",
        column = @Column(table = "article_content", name = "content")),
    @AttributeOverride(
        name = "contentType",
        column = @Column(table = "article_content", name = "content_type"))
  })
  @Embedded
  private ArticleContent content;

  protected Article() {}

  public Article(String title, ArticleContent content) {
    this.title = title;
    this.content = content;
  }
}
