package com.juny.dddstart2.catalog.command.domain.product;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "image_type")
@Table(name = "image")
public abstract class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "image_id")
  private Long id;

  @Column(name = "iamge_path")
  @Getter(AccessLevel.PROTECTED)
  private String path;

  @Column(name = "upload_time")
  private LocalDateTime uploadTime;

  protected Image() {}

  public Image(String path) {
    this.path = path;
    this.uploadTime = LocalDateTime.now();
  }

  public abstract String getUrl();

  public abstract boolean hasThumbnail();

  public abstract String getThumbnailUrl();
}
