package com.juny.dddstart2.springconfig;

import com.juny.dddstart2.board.Article;
import com.juny.dddstart2.board.ArticleContent;
import com.juny.dddstart2.board.ArticleRepository;
import com.juny.dddstart2.catalog.command.domain.category.Category;
import com.juny.dddstart2.catalog.command.domain.category.CategoryId;
import com.juny.dddstart2.catalog.command.domain.category.CategoryRepository;
import com.juny.dddstart2.catalog.command.domain.product.ExternalImage;
import com.juny.dddstart2.catalog.command.domain.product.Image;
import com.juny.dddstart2.catalog.command.domain.product.Product;
import com.juny.dddstart2.catalog.command.domain.product.ProductId;
import com.juny.dddstart2.catalog.command.domain.product.ProductRepository;
import com.juny.dddstart2.common.model.Money;
import java.util.ArrayList;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DummyData implements CommandLineRunner {

  private final ArticleRepository articleRepository;
  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;

  public DummyData(ArticleRepository articleRepository, CategoryRepository categoryRepository,
      ProductRepository productRepository) {
    this.articleRepository = articleRepository;
    this.categoryRepository = categoryRepository;
    this.productRepository = productRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Loading initial data...");
    for(int i = 0; i < 20; i++) {
      ArticleContent articleContent = new ArticleContent("content" + i, "type" + i);
      Article article = new Article("title" + i, articleContent);
      articleRepository.save(article);

      CategoryId categoryId = new CategoryId(7828L + i);
      Category category = new Category(categoryId, "category name" + i);
      categoryRepository.save(category);

      Image image = null;
      ArrayList<Image> images = new ArrayList<>();
      for(int j = 0; j < 10; j++) {
        images.add(new ExternalImage("/hello" + j));
      }
      ProductId productId = new ProductId("1234" + i);
      Money money = new Money(5000 + i);
      Product product = new Product(productId, "product" + i, money, "details" + i, images);
      product.addCategory(categoryId);
      productRepository.save(product);
    }
    System.out.println("Data loaded.");
  }
}
