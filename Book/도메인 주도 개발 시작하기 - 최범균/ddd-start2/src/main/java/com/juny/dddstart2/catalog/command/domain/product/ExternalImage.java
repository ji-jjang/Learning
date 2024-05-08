package com.juny.dddstart2.catalog.command.domain.product;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EI")
public class ExternalImage extends Image {

  protected ExternalImage() {}

  public ExternalImage(String path){
    super(path);
  }

  @Override
  public String getUrl() {
    return getPath();
  }

  @Override
  public boolean hasThumbnail() {
    return false;
  }

  @Override
  public String getThumbnailUrl() {
    return null;
  }
}
