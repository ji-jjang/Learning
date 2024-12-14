package com.juny.locktest.optimisticlock;

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
public class OptimisticLockTest {

  @Autowired private ReservationService reservationService;

  @Test
  @DisplayName("낙관적 락을 사용할 때 조회한 슬롯을 한 번에 업데이트 한다.")
  void createReservationOptimisticBulkUpdate() throws InterruptedException {

    List<Long> timeSlotIds = List.of(1L, 2L, 3L, 4L);

    ExecutorService executor = Executors.newFixedThreadPool(1000);

    List<Reservation> reservations = Collections.synchronizedList(new ArrayList<>());

    for (int i = 0; i < 100; ++i) {
      executor.execute(
          () -> {
            try {
              Reservation reservation =
                  reservationService.createReservationOptimisticBulk(timeSlotIds);
              reservations.add(reservation);
            } catch (Exception e) {
              System.err.println(Thread.currentThread().getName() + " failed: " + e.getMessage());
            }
          });
    }

    executor.shutdown();
    executor.awaitTermination(5, TimeUnit.SECONDS);

    Assertions.assertThat(reservations.size()).isEqualTo(1);
  }

  @Test
  @DisplayName("낙관적 락을 사용할 때 조회한 슬롯들을 한 번에 수정하지 않으면 병행 제어가 되지 않는다.")
  void createReservationOptimisticFailed() throws InterruptedException {

    List<Long> timeSlotIds = List.of(1L, 2L, 3L, 4L);

    ExecutorService executor = Executors.newFixedThreadPool(100);

    List<Reservation> reservations = Collections.synchronizedList(new ArrayList<>());

    for (int i = 0; i < 100; ++i) {
      executor.execute(
          () -> {
            try {
              Reservation reservation = reservationService.createReservationOptimistic(timeSlotIds);
              reservations.add(reservation);
              System.out.println("ThreadInfo: " + Thread.currentThread());
            } catch (Exception e) {
              System.err.println(Thread.currentThread().getName() + " failed: " + e.getMessage());
            }
          });
    }

    executor.shutdown();
    executor.awaitTermination(5, TimeUnit.SECONDS);

    System.out.println("reservations = " + reservations);
    Assertions.assertThat(reservations.size()).isEqualTo(1);
  }

  @Test
  void createReservationOptimisticSuccessOnSingleThread() throws InterruptedException {
    List<Long> timeSlotIds = List.of(1L, 2L, 3L, 4L);

    ExecutorService executor = Executors.newFixedThreadPool(2);

    List<Reservation> reservations = Collections.synchronizedList(new ArrayList<>());

    Runnable task1 =
        () -> {
          Reservation reservation = reservationService.createReservationOptimistic(timeSlotIds);
          reservations.add(reservation);
        };

    executor.execute(task1);

    executor.shutdown();
    executor.awaitTermination(5, TimeUnit.SECONDS);

    Assertions.assertThat(reservations.size()).isEqualTo(1);
  }
}
