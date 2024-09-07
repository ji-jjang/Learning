package com.example.valueobjectcollection.list;

import jakarta.persistence.Embeddable;

@Embeddable
public class Choice {
  private String text;
  private boolean input;
}
