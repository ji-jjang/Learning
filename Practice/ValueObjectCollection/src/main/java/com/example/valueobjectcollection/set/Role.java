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
@Table(name = "roles")
@NoArgsConstructor
@Getter
@Setter
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "role_perm", joinColumns = @JoinColumn(name = "role_id")) // 롤에 있는 id 컬럼을 참조, 조인할 때 사용할 컬럼 지정
  @Column(name = "perm") // 실제 값을 가지고 있는 컬럼
  private Set<String> permissions = new HashSet<>();

  public Role(String name, Set<String> permissions) {
    this.name = name;
    this.permissions = permissions;
  }
}
