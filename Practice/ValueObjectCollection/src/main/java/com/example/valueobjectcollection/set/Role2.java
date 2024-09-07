package com.example.valueobjectcollection.set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles2")
@NoArgsConstructor
@Getter
@Setter
public class Role2 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "role_perm2", joinColumns = @JoinColumn(name = "roles2_id"))
  private Set<GrantedPermission> permissions = new HashSet<>();
}
