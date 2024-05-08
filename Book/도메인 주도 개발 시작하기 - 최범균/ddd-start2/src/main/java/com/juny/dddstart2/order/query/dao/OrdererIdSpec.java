package com.juny.dddstart2.order.query.dao;

import com.juny.dddstart2.order.query.dto.OrderSummary;
import com.juny.dddstart2.order.query.dto.OrderSummary_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class OrdererIdSpec implements Specification<OrderSummary> {
  private String ordererId;

  public OrdererIdSpec(String ordererId) {
    this.ordererId = ordererId;
  }

  @Override
  public Predicate toPredicate(Root<OrderSummary> root,
      CriteriaQuery<?> query,
      CriteriaBuilder cb) {
    return cb.equal(root.get(OrderSummary_.ordererId), ordererId);
  }
}
