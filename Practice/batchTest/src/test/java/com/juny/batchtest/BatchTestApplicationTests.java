package com.juny.batchtest;

import com.juny.batchtest.domain.monitoring.sale.OrderRepository;
import com.juny.batchtest.domain.monitoring.sale.Sale;
import com.juny.batchtest.domain.monitoring.sale.SaleRepository;
import com.juny.batchtest.domain.others.Order;
import com.juny.batchtest.domain.others.OrderStatus;
import java.time.LocalDateTime;
import java.util.Random;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BatchTestApplicationTests {

  @Autowired SaleRepository saleRepository;
  @Autowired
  OrderRepository orderRepository;

  @Test
  void contextLoads() {
  }

  @Test
  public void CreateDummyData() {

    for(int i = 0; i < 500; i++) {
            Sale sale = new Sale(LocalDateTime.of(2024,5,31,23, 1, 0).minusHours(i), 100000 + 10L *
       i,  i+ 0L);

      saleRepository.save(sale);
    }
  }

}
