package com.juny.dddstart2.order.query.application;

import com.juny.dddstart2.catalog.query.product.ProductData;
import com.juny.dddstart2.catalog.query.product.ProductQueryService;
import com.juny.dddstart2.order.command.domain.Order;
import com.juny.dddstart2.order.command.domain.OrderNo;
import com.juny.dddstart2.order.command.domain.OrderRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderDetailService {
  private OrderRepository orderRepository;
  private ProductQueryService productQueryService;

  public OrderDetailService(OrderRepository orderRepository,
      ProductQueryService productQueryService) {
    this.orderRepository = orderRepository;
    this.productQueryService = productQueryService;
  }

  @Transactional
  public Optional<OrderDetail> getOrderDetail(String orderNumber) {
    Optional<Order> orderOpt = orderRepository.findById(new OrderNo(orderNumber));
    return orderOpt.map(order -> {
      List<OrderLineDetail> orderLines = order.getOrderLines().stream()
          .map(orderLine -> {
            Optional<ProductData> productOpt =
                productQueryService.getProduct(orderLine.getProductId().getId());
            return new OrderLineDetail(orderLine, productOpt.orElse(null));
          }).collect(Collectors.toList());
      return new OrderDetail(order, orderLines);
    });
  }
}
