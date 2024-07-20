package com.example.valueobjectcollection.set;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class GrantedPermission {

  @Column(name = "perm")
  private String permission;
  private String grantor;
}
