package com.juny.batchtest.scheduler;

import com.juny.batchtest.domain.monitoring.sale.OrderRepository;
import com.juny.batchtest.domain.monitoring.sale.ResCreateSale;
import com.juny.batchtest.domain.monitoring.sale.Sale;
import com.juny.batchtest.domain.monitoring.sale.SaleRepository;
import com.juny.batchtest.domain.monitoring.sale.SaleStatisticMapper;
import com.querydsl.core.Tuple;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaleStatisticScheduler {

  private final OrderRepository orderRepository;
  private final SaleStatisticMapper saleStatisticMapper;
  private final SaleRepository saleRepository;

  @Scheduled(cron = "0 0 * * * *")
  public void confirmPendingOrder() {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime start = now.minusDays(1).minusHours(1);
    LocalDateTime end = now.minusDays(1);

    orderRepository.cancelPendingOrders(start, end);
  }

  @Scheduled(cron = "0 0 * * * *")
  public void calculateHourlySale() {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime start = now.minusDays(1).minusHours(1);
    LocalDateTime end = now.minusDays(1);

    Tuple tupleSale = orderRepository.getSaleByOrders(start, end);

    ResCreateSale resCreateSale = saleStatisticMapper.toResCreateSale(tupleSale);

    Sale sale = new Sale(now, resCreateSale.totalSales(), resCreateSale.totalOrders());
    saleRepository.save(sale);
  }
}
