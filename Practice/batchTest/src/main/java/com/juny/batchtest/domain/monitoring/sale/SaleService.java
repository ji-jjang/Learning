package com.juny.batchtest.domain.monitoring.sale;

import com.querydsl.core.Tuple;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleService {

  private static final HashMap<String, ChronoUnit> timeMapper = new HashMap<String, ChronoUnit>();

  private final OrderRepository orderRepository;
  private final SaleRepository saleRepository;

  private final SaleStatisticMapper saleStatisticMapper;

  static {
    timeMapper.put("hourly", ChronoUnit.HOURS);
    timeMapper.put("daily", ChronoUnit.DAYS);
    timeMapper.put("weekly", ChronoUnit.WEEKS);
    timeMapper.put("monthly", ChronoUnit.MONTHS);
  }

  public ResSale getSales(String timeUnit, LocalDateTime start, LocalDateTime end){

    ChronoUnit chronoUnit = validateTimeIntervals(timeUnit, start, end);

    if (chronoUnit == ChronoUnit.WEEKS){
      List<Object[]> sales = saleRepository.findSalesByWeek(start, end);
      List<Object[]> refunds = orderRepository.findRefundsByWeek(start, end);
      return saleStatisticMapper.toResSaleFromObjects(sales, refunds);
    }

    List<Tuple> sales = orderRepository.findSalesByTimeUnit(chronoUnit, start, end);
    List<Tuple> refunds = saleRepository.findRefundsByTimeUnit(chronoUnit, start, end);
    return saleStatisticMapper.toResSale(sales, refunds);
  }

  private ChronoUnit validateTimeIntervals(String timeUnit, LocalDateTime start, LocalDateTime end) {
    if (Objects.isNull(timeUnit) || Objects.isNull(start) || Objects.isNull(end)){
      throw new IllegalArgumentException("parameter is null.");
    }

    ChronoUnit chronoUnit = timeMapper.get(timeUnit);
    if (chronoUnit == null){
      throw new IllegalArgumentException("unsupported time unit.");
    }

    long timeIntervalCount = calculateTimeInterval(chronoUnit, start, end);
    System.out.println("timeIntervalCount = " + timeIntervalCount);

    if (timeIntervalCount <= 0){
      throw new IllegalArgumentException("the selected time period is short.");
    }
    return chronoUnit;
  }

  private long calculateTimeInterval(ChronoUnit chronoUnit, LocalDateTime start, LocalDateTime end) {

    LocalDate now = LocalDate.now();

    return chronoUnit.between(start, end);
  }
}
