package com.juny.batchtest.domain.monitoring.sale;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SaleRepository extends JpaRepository<Sale, Long>, SaleStatisticRepository {

  @Query(value = "SELECT DATE_FORMAT(DATE_SUB(s.date_time, INTERVAL (DAYOFWEEK(s.date_time) - DAYOFWEEK(:start)) DAY), '%Y-%m-%d') AS week, " +
      "SUM(s.total_sales) AS totalSales, " +
      "COUNT(s.total_sales) " +
      "FROM sales s " +
      "WHERE s.date_time BETWEEN :start AND :end " +
      "GROUP BY week " +
      "ORDER BY week", nativeQuery = true)
  List<Object[]> findSalesByWeek(@Param("start") LocalDateTime start,
      @Param("end") LocalDateTime end);
}
