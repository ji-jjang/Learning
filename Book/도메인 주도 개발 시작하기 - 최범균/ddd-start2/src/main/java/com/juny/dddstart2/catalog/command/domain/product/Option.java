package com.juny.dddstart2.catalog.command.domain.product;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
public class Option {
  @Column(name = "option_value")
  private String value;
  @Column(name = "option_title")
  private String title;

  public Option() {}

  public Option(String value, String title) {
    this.value = value;
    this.title = title;
  }
}
