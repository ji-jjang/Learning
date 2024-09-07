package com.juny.batchaggregation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sales")
@NoArgsConstructor
public class Refund {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column
  private LocalDateTime dateTime;

  @Column
  private Long totalRefundsAmount;

  @Column
  private Long totalOrdersCount;

  public Refund(LocalDateTime dateTime, Long totalRefundsAmount, Long totalOrdersCount) {
    this.dateTime = dateTime;
    this.totalRefundsAmount = totalRefundsAmount;
    this.totalOrdersCount = totalOrdersCount;
  }
}