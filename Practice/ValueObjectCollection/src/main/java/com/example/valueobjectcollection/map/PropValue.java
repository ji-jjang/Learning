package com.example.valueobjectcollection.map;

import jakarta.persistence.Embeddable;

@Embeddable
public class PropValue {
  private String value;
  private boolean enabled;
}
