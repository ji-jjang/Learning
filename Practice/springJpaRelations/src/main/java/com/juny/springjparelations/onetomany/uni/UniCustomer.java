package com.juny.springjparelations.onetomany.uni;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "customers")
public class UniCustomer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToMany
  @JoinColumn(name = "customer_id")
  private List<UniSpace> uniSpaces = new ArrayList<>();

  public UniCustomer(String name) {
    this.name = name;
  }

  // OneToMany 연관관계 편의 메서드, 고객 - 공간 [단방향]
  public void addSpace(UniSpace uniSpace) {
    this.uniSpaces.add(uniSpace);
  }
}
