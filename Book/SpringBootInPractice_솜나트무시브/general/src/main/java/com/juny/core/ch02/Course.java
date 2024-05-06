package com.juny.core.ch02;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
  private Long id;
  private String name;
  private String category;

  @Min(value = 1, message = "A course should have a minimum of 1 rating")
  @Max(value = 5, message = "A course should have a maximum of 5 rating")
  private int rating;

  private String description;
}
