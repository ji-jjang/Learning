package com.juny.locktest.racecondition;

import com.juny.locktest.Reservation;
import com.juny.locktest.ReservationService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RaceConditionTest {

  @Autowired private ReservationService reservationService;

  @Test
  @DisplayName("예약 생성 경쟁 조건이 발생하여 같은 슬롯에 2개의 예약이 생성된다.")
  void createReservationRaceCondition() throws InterruptedException {

    List<Long> timeSlotIds = List.of(1L, 2L, 3L, 4L);

    ExecutorService executor = Executors.newFixedThreadPool(2);

    List<Reservation> reservations = Collections.synchronizedList(new ArrayList<>());

    Runnable task1 =
        () -> {
          Reservation reservation = reservationService.createReservationRaceCondition(timeSlotIds);
          reservations.add(reservation);
        };

    Runnable task2 =
        () -> {
          Reservation reservation = reservationService.createReservationRaceCondition(timeSlotIds);
          reservations.add(reservation);
        };

    executor.execute(task1);
    executor.execute(task2);

    executor.shutdown();
    executor.awaitTermination(5, TimeUnit.SECONDS);

    Assertions.assertThat(reservations.size()).isEqualTo(2);
  }
}
