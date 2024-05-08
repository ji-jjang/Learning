package com.juny.dddstart2.order.query.dao;

import com.juny.dddstart2.order.query.dto.OrderSummary;
import com.juny.dddstart2.order.query.dto.OrderSummary_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.time.LocalDateTime;
import org.springframework.data.jpa.domain.Specification;

public class OrderSummarySpecs {
  public static Specification<OrderSummary> ordererId(String ordererId) {
    return (Root<OrderSummary> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
        cb.equal(root.<String>get("ordererId"), ordererId);
  }

  public static Specification<OrderSummary> orderDateBetween(
      LocalDateTime from, LocalDateTime to) {
    return (Root<OrderSummary> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
        cb.between(root.get(OrderSummary_.orderDate), from, to);
  }
}
