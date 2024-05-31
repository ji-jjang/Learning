package com.juny.batchtest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CalculateTime {

  public static void main(String[] args) {
    LocalDateTime startTime = LocalDateTime.of(2023, 4, 1, 0, 0);
    LocalDateTime endTime = LocalDateTime.of(2023, 4, 8, 0, 0);

    long hourlyIntervals = calculateIntervals(startTime, endTime, ChronoUnit.HOURS);
    long dailyIntervals = calculateIntervals(startTime, endTime, ChronoUnit.DAYS);
    long weeklyIntervals = calculateIntervals(startTime, endTime, ChronoUnit.WEEKS);
    long monthlyIntervals = calculateIntervals(startTime, endTime, ChronoUnit.MONTHS);
    long yearlyIntervals = calculateIntervals(startTime, endTime, ChronoUnit.YEARS);

    System.out.println("Hourly intervals: " + hourlyIntervals);
    System.out.println("Daily intervals: " + dailyIntervals);
    System.out.println("Weekly intervals: " + weeklyIntervals);
    System.out.println("Monthly intervals: " + monthlyIntervals);
    System.out.println("Yearly intervals: " + yearlyIntervals);
  }

  private static long calculateIntervals(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit unit) {
    return unit.between(startTime, endTime);
  }

}
