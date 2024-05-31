package com.juny.batchtest.domain.monitoring.sale;

import com.juny.batchtest.domain.others.Order;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long>, SaleStatisticRepository {

  @Query("SELECT o FROM Order o WHERE o.orderStatus = 'COMPLETE' AND o.orderDate BETWEEN :startTime AND :endTime")
  List<Order> findCompletedOrdersWithinTimeRange(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

  @Query(value = "SELECT DATE_FORMAT(DATE_SUB(o.order_date, INTERVAL (DAYOFWEEK(o.order_date) - DAYOFWEEK(:start)) DAY), '%Y-%m-%d') AS week, " +
      "SUM(o.total_price) AS totalPrice, " +
      "COUNT(o.total_price) " +
      "FROM order s " +
      "WHERE o BETWEEN :start AND :end " +
      "AND o.order_status = 'CANCEL' " +
      "GROUP BY week " +
      "ORDER BY week", nativeQuery = true)
  List<Object[]> findRefundsByWeek(@Param("start") LocalDateTime start,
      @Param("end") LocalDateTime end);
}
