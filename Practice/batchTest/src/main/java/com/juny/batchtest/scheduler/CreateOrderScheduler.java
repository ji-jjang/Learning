package com.juny.batchtest.scheduler;

import com.juny.batchtest.domain.others.Category;
import com.juny.batchtest.domain.others.CategoryRepository;
import com.juny.batchtest.domain.others.Deliveries;
import com.juny.batchtest.domain.others.Order;
import com.juny.batchtest.domain.others.OrderItem;
import com.juny.batchtest.domain.others.OrderItemRepository;
import com.juny.batchtest.domain.monitoring.sale.OrderRepository;
import com.juny.batchtest.domain.others.OrderStatus;
import com.juny.batchtest.domain.others.Product;
import com.juny.batchtest.domain.others.ProduectRepository;
import com.juny.batchtest.domain.others.Subcategory;
import com.juny.batchtest.domain.others.SubcategoryRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderScheduler {

  private final OrderRepository orderRepository;
  private final ProduectRepository produectRepository;
  private final CategoryRepository categoryRepository;
  private final SubcategoryRepository subcategoryRepository;
  private final OrderItemRepository orderItemRepository;

  public CreateOrderScheduler(OrderRepository orderRepository,
      ProduectRepository produectRepository, CategoryRepository categoryRepository,
      SubcategoryRepository subcategoryRepository, OrderItemRepository orderItemRepository) {
    this.orderRepository = orderRepository;
    this.produectRepository = produectRepository;
    this.categoryRepository = categoryRepository;
    this.subcategoryRepository = subcategoryRepository;
    this.orderItemRepository = orderItemRepository;
  }

  @Scheduled(cron = "* * * * * *")
  @Transactional
  public void createOrderByAuto() {

    Category category = new Category("categoryName");
    categoryRepository.save(category);
    Subcategory subcategory = new Subcategory("subcategoryName", category.getId());
    subcategoryRepository.save(subcategory);

    Product product =
        new Product(
            "name",
            "description",
            5000,
            LocalDate.now(),
            22.2F,
            "imageURL",
            10,
            subcategory.getId());

    produectRepository.save(product);

    OrderItem orderItem1 = OrderItem.createOrderItem(product, 20000, 5);
    OrderItem orderItem2 = OrderItem.createOrderItem(product, 30000, 6);
    OrderItem orderItem3 = OrderItem.createOrderItem(product, 40000, 8);
    orderItemRepository.save(orderItem1);
    orderItemRepository.save(orderItem2);
    orderItemRepository.save(orderItem3);

    List<OrderItem> orderItems = new ArrayList<>();
    orderItems.add(orderItem1);
    orderItems.add(orderItem2);
    orderItems.add(orderItem3);
    System.out.println("orderItems.size() = " + orderItems.size());

    Order order = Order.createOrder(
        1L,
        Deliveries.createDelivery(
            "1", "1", "1", "good", "request", LocalDate.of(2024, 5, 31), 01022345454, "name"),
        orderItems);
    order.setOrderStatus(OrderStatus.ORDER);

    orderRepository.save(order);
  }
}
