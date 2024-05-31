package com.juny.batchtest.domain.monitoring.sale;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sales")
@Data
@NoArgsConstructor
public class Sale {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime dateTime;
  private Long totalSales;
  private Long totalOrders;

  public Sale(LocalDateTime dateTime, Long totalSales, Long totalOrders) {
    this.dateTime = dateTime;
    this.totalSales = totalSales;
    this.totalOrders = totalOrders;
  }
}
