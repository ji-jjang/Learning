package com.juny.dddstart2.order.query.dao;

import com.juny.dddstart2.order.query.dto.OrderSummary;
import com.juny.dddstart2.order.query.dto.OrderView;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface OrderSummaryDao extends Repository<OrderSummary, String> {
  List<OrderSummary> findByOrdererId(String ordererId);
  List<OrderSummary> findByOrdererId(String ordererId, Sort sort);
  List<OrderSummary> findByOrdererId(String ordererId, Pageable pageable);
  List<OrderSummary> findByOrdererIdOrderByNumberDesc(String ordererId);

  List<OrderSummary> findAll(Specification<OrderSummary> spec);
  List<OrderSummary> findAll(Specification<OrderSummary> spec, Sort sort);
  List<OrderSummary> findAll(Specification<OrderSummary> spec, Pageable pageable);

  Page<OrderSummary> findAll(Pageable pageable);

  @Query("""    
            select new com.juny.dddstart2.order.query.dto.OrderView(
                o.number, o.state, m.name, m.id, p.name
            )
            from Order o join o.orderLines ol, Member m, Product p
            where o.orderer.memberId.id = :ordererId
            and o.orderer.memberId.id = m.id
            and index(ol) = 0
            and ol.productId.id = p.id
            order by o.number.number desc
            """)
  List<OrderView> findOrderView(String ordererId);
}

