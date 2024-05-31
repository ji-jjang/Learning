package com.juny.batchtest.domain.monitoring.sale;

import com.querydsl.core.Tuple;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SaleStatisticMapper {

  public ResSale toResSale(List<Tuple> sales, List<Tuple> refunds) {
    String[] dateTimeArray = sales.stream()
        .map(tuple -> tuple.get(0, String.class))
        .toArray(String[]::new);
    Long[] totalSalesArray = sales.stream()
        .map(tuple -> tuple.get(1, Long.class))
        .toArray(Long[]::new);
    Integer[] totalOrdersArray = sales.stream()
        .map(tuple -> tuple.get(2, Integer.class))
        .toArray(Integer[]::new);

    Long[] totalRefundsArray = refunds.stream()
        .map(tuple -> tuple.get(1, Long.class))
        .toArray(Long[]::new);

    return new ResSale(dateTimeArray, totalSalesArray, totalOrdersArray, totalRefundsArray);
  }

  public ResSale toResSaleFromObjects(List<Object[]> sales, List<Object[]> refunds) {
    String[] dateTimeArray = sales.stream()
        .map(array -> (String) array[0])
        .toArray(String[]::new);
    Long[] totalSalesArray = sales.stream()
        .map(array -> ((Number) array[1]).longValue())
        .toArray(Long[]::new);
    Integer[] totalOrdersArray = sales.stream()
        .map(array -> ((Number) array[2]).intValue())
        .toArray(Integer[]::new);

    Long[] totalRefundsArray = refunds.stream()
        .map(array -> ((Number) array[1]).longValue())
        .toArray(Long[]::new);

    return new ResSale(dateTimeArray, totalSalesArray, totalOrdersArray, totalRefundsArray);
  }


  public ResCreateSale toResCreateSale(Tuple tupleSale) {

    return new ResCreateSale(tupleSale.get(0, Long.class), tupleSale.get(1, Long.class));
  }
}

