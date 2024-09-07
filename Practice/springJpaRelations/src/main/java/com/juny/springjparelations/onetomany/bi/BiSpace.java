package com.juny.springjparelations.onetomany.bi;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "spaces_bi")
public class BiSpace {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne
  @JoinColumn(name = "bi_customer_id")
  private BiCustomer biCustomer;

  public BiSpace(String name) {
    this.name = name;
  }

  // ManyToOne 연관관계 편의 메서드, 공간 - 고객 [양방향]
  public void setBiCustomer(BiCustomer biCustomer) {
    if (this.biCustomer != null) {
      this.biCustomer.getBiSpaces().remove(this);
    }
    this.biCustomer = biCustomer;
    biCustomer.getBiSpaces().add(this);
  }
}
