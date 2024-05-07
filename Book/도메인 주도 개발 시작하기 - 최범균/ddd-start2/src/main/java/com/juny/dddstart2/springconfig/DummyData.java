package com.juny.dddstart2.springconfig;

import com.juny.dddstart2.board.Article;
import com.juny.dddstart2.board.ArticleContent;
import com.juny.dddstart2.board.ArticleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DummyData implements CommandLineRunner {

  private final ArticleRepository articleRepository;

  public DummyData(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Loading initial data...");
    for(int i = 0; i < 20; i++) {
      ArticleContent articleContent = new ArticleContent("content" + i, "type" + i);
      Article article = new Article("title" + i, articleContent);
      articleRepository.save(article);
    }
    System.out.println("Data loaded.");
  }
}
