package com.juny.locktest.pessimisticlock;

import com.juny.locktest.Reservation;
import com.juny.locktest.ReservationService;
import com.juny.locktest.TimeSlot;
import com.juny.locktest.TimeSlotService;
import java.time.LocalDate;
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
public class PessimisticLockTest {

  @Autowired private TimeSlotService timeSlotService;

  @Autowired private ReservationService reservationService;

  @Test
  @DisplayName("격리 수준 READ-UNCOMMITED 슬롯이 커밋되지 않고 수정 중에 있는 내용을 보게 된다.")
  void createReservationAndRead2() throws InterruptedException {

    List<Long> timeSlotIds = List.of(1L, 2L, 3L, 4L);

    ExecutorService executor = Executors.newFixedThreadPool(4);

    Runnable task1 =
        () -> {
          reservationService.createReservationRaceCondition(timeSlotIds);
        };

    Runnable task2 =
        () -> {
          try {
            Thread.sleep(30);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          List<TimeSlot> timeSlot =
              timeSlotService.getTimeSlotUnCommited(LocalDate.of(2024, 12, 15));
          System.out.println("ThreadInfo: " + Thread.currentThread() + " " + timeSlot);
        };
    Runnable task3 =
        () -> {
          try {
            Thread.sleep(40);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }

          List<TimeSlot> timeSlot =
              timeSlotService.getTimeSlotUnCommited(LocalDate.of(2024, 12, 15));
          System.out.println("ThreadInfo: " + Thread.currentThread() + " " + timeSlot);
        };
    Runnable task4 =
        () -> {
          try {
            Thread.sleep(50);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          List<TimeSlot> timeSlot =
              timeSlotService.getTimeSlotUnCommited(LocalDate.of(2024, 12, 15));
          System.out.println("ThreadInfo: " + Thread.currentThread() + " " + timeSlot);
        };

    executor.execute(task1);
    executor.execute(task2);
    executor.execute(task3);
    executor.execute(task4);

    executor.shutdown();
    executor.awaitTermination(5, TimeUnit.SECONDS);
  }

  @Test
  @DisplayName("MySQL 기본 격리 수준은 REPEATABLE_READ, 비관적 읽기 락을 사용하지 않아도 일관성 보장된다.")
  void createReservationAndRead() throws InterruptedException {

    List<Long> timeSlotIds = List.of(1L, 2L, 3L, 4L);

    ExecutorService executor = Executors.newFixedThreadPool(4);

    Runnable task1 =
        () -> {
          reservationService.createReservationRaceCondition(timeSlotIds);
        };

    Runnable task2 =
        () -> {
          try {
            Thread.sleep(30);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          List<TimeSlot> timeSlot = timeSlotService.getTimeSlot(LocalDate.of(2024, 12, 15));
          System.out.println("ThreadInfo: " + Thread.currentThread() + " " + timeSlot);
        };
    Runnable task3 =
        () -> {
          try {
            Thread.sleep(40);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }

          List<TimeSlot> timeSlot = timeSlotService.getTimeSlot(LocalDate.of(2024, 12, 15));
          System.out.println("ThreadInfo: " + Thread.currentThread() + " " + timeSlot);
        };
    Runnable task4 =
        () -> {
          try {
            Thread.sleep(50);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          List<TimeSlot> timeSlot = timeSlotService.getTimeSlot(LocalDate.of(2024, 12, 15));
          System.out.println("ThreadInfo: " + Thread.currentThread() + " " + timeSlot);
        };

    executor.execute(task1);
    executor.execute(task2);
    executor.execute(task3);
    executor.execute(task4);

    executor.shutdown();
    executor.awaitTermination(5, TimeUnit.SECONDS);
  }

  @Test
  @DisplayName("비관적 읽기 락으로 읽고, 수정하는 메서드에 여러 쓰레드가 접근하면 데드락이 발생한다")
  void createReservationPessimisticReadLockWithUpdateDeadlock() throws InterruptedException {

    List<Long> timeSlotIds = List.of(1L, 2L, 3L, 4L);

    ExecutorService executor = Executors.newFixedThreadPool(2);

    List<Reservation> reservations = Collections.synchronizedList(new ArrayList<>());

    Runnable task1 =
        () -> {
          Reservation reservation =
              reservationService.createReservationPessimisticReadLock(timeSlotIds);
          reservations.add(reservation);
        };

    Runnable task2 =
        () -> {
          Reservation reservation =
              reservationService.createReservationPessimisticReadLock(timeSlotIds);
          reservations.add(reservation);
        };

    executor.execute(task1);
    executor.execute(task2);

    executor.shutdown();
    executor.awaitTermination(5, TimeUnit.SECONDS);

    Assertions.assertThat(reservations.size()).isEqualTo(1);
  }

  @Test
  @DisplayName("비관적 쓰기 락, 2개의 쓰레드를 실행시키고, SELECT FOR UPDATE 되면, 다른 쓰레드는 대기한다")
  void createReservationPessimisticWriteLock2() throws InterruptedException {

    List<Long> timeSlotIds = List.of(1L, 2L, 3L, 4L);

    ExecutorService executor = Executors.newFixedThreadPool(100);

    List<Reservation> reservations = Collections.synchronizedList(new ArrayList<>());

    Runnable task1 =
        () -> {
          Reservation reservation = null;
          try {
            reservation = reservationService.createReservationPessimisticWriteLock(timeSlotIds);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          reservations.add(reservation);
        };

    Runnable task2 =
        () -> {
          Reservation reservation = null;
          try {
            reservation = reservationService.createReservationPessimisticWriteLock(timeSlotIds);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          reservations.add(reservation);
        };

    executor.execute(task1);
    executor.execute(task2);

    executor.shutdown();
    executor.awaitTermination(5, TimeUnit.SECONDS);

    Assertions.assertThat(reservations.size()).isEqualTo(1);
  }

  @Test
  @DisplayName("비관적 쓰기 락, 100개의 쓰레드를 거의 동시에 실행시켜도 하나의 예약이 생성된다.")
  void createReservationPessimisticWriteLock() throws InterruptedException {
    List<Long> timeSlotIds = List.of(1L, 2L, 3L, 4L);

    ExecutorService executor = Executors.newFixedThreadPool(100);

    List<Reservation> reservations = Collections.synchronizedList(new ArrayList<>());

    for (int i = 0; i < 100; ++i) {
      executor.execute(
          () -> {
            try {
              Reservation reservation =
                  reservationService.createReservationPessimisticWriteLock(timeSlotIds);
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
}
