package com.juny.webclient.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
  private String id;

  private String name;

  private String category;

  private int rating;

  private String description;
}
