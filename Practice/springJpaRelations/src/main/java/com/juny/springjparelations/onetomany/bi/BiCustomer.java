package com.juny.springjparelations.onetomany.bi;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "customers_bi")
public class BiCustomer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

//  @OneToMany(mappedBy = "biCustomer", cascade = CascadeType.ALL, orphanRemoval = true)
  @OneToMany(mappedBy = "biCustomer")
  private List<BiSpace> biSpaces = new ArrayList<>();

//  @OneToMany(mappedBy = "biCustomer")
//  private Set<BiSpace> biSpaces = new HashSet<>();

  public BiCustomer(String name) {
    this.name = name;
  }

  // OneToMany 연관관계 편의 메서드, 고객 - 공간 [양방향]
  public void addSpace(BiSpace biSpace) {
    this.biSpaces.add(biSpace);
    if (biSpace.getBiCustomer() != this) {
      biSpace.setBiCustomer(this);
    }
  }

  // OneToMany 연관관계 편의 메서드, 고객 - 공간 [양방향]
  public void removeSpace(BiSpace biSpace) {
    this.biSpaces.remove(biSpace);
    if (biSpace.getBiCustomer() == this) {
      biSpace.setBiCustomer(null);
    }
  }
}
