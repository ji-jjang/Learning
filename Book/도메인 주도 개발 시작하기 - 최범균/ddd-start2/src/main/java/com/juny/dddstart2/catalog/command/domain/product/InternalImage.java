package com.juny.dddstart2.catalog.command.domain.product;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("I1")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InternalImage extends Image {

  public InternalImage(String path) {
    super(path);
  }

  @Override
  public String getUrl() {
    return "/images/original/" + getPath();
  }

  @Override
  public boolean hasThumbnail() {
    return true;
  }

  @Override
  public String getThumbnailUrl() {
    return "/images/thumbnail/" + getPath();
  }
}
