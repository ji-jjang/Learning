package com.juny.batchtest.domain.monitoring.sale;

import lombok.ToString;

public record ResSale(
    String[] dateTime,
    Long[] totalSales,
    Integer[] totalOrders,
    Long[] totalRefunds
) {}
