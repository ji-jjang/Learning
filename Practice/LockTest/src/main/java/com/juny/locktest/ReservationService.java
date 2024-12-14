package com.juny.locktest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

  private final TimeSlotRepository timeSlotRepository;
  private final ReservationRepository reservationRepository;
  private final TimeSlotVersioningRepository timeSlotVersioningRepository;

  // Select For Update, 비관적 읽기락 (다른 쓰레드는 조회 가능 )
  @Transactional
  public Reservation createReservationPessimisticReadLock(List<Long> timeSlotIds) {

    List<TimeSlot> timeSlots = timeSlotRepository.findByIdsForShare(timeSlotIds);

    for (var timeSlot : timeSlots) {
      if (timeSlot.getIsReserved()) {
        throw new RuntimeException("TimeSlot is reserved");
      }
    }

    LocalDateTime startTime = null;
    LocalDateTime endTime = null;
    for (TimeSlot timeSlot : timeSlots) {
      startTime = getStartTime(startTime, timeSlot.getStartTime());
      endTime = getEndTime(endTime, timeSlot.getEndTime());

      timeSlot.setIsReserved(true);
      timeSlotRepository.updateIsReservedTrue(timeSlot);
    }
    Reservation reservation = new Reservation(null, startTime, endTime, 10000);
    reservationRepository.save(reservation);

    return reservation;
  }

  /**
   *
   *
   * <h1>비관적 쓰기 락, 조회 시 다른 쓰레드가 수정 및 조회를 할 수 없다.</h1>
   *
   * @param timeSlotIds
   * @return
   * @throws InterruptedException
   */
  @Transactional
  public Reservation createReservationPessimisticWriteLock(List<Long> timeSlotIds)
      throws InterruptedException {

    System.out.println("비관적 쓰기 락 조회 전" + Thread.currentThread().getName());
    List<TimeSlot> timeSlots = timeSlotRepository.findByIdsForUpdate(timeSlotIds);
    System.out.println("비관적 쓰기 락 조회 후" + Thread.currentThread().getName());
    Thread.sleep(3000);

    for (var timeSlot : timeSlots) {
      if (timeSlot.getIsReserved()) {
        throw new RuntimeException("TimeSlot is reserved");
      }
    }

    LocalDateTime startTime = null;
    LocalDateTime endTime = null;
    for (TimeSlot timeSlot : timeSlots) {
      startTime = getStartTime(startTime, timeSlot.getStartTime());
      endTime = getEndTime(endTime, timeSlot.getEndTime());

      timeSlot.setIsReserved(true);
      timeSlotRepository.updateIsReservedTrue(timeSlot);
    }
    Reservation reservation = new Reservation(null, startTime, endTime, 10000);
    reservationRepository.save(reservation);

    return reservation;
  }

  /**
   *
   *
   * <h1>낙관적 락 사용, 조회 된 슬롯을 한 번에 수정하여 경쟁 상태가 발생하지 않도록 한다.</h1>
   *
   * @param timeSlotIds
   * @return
   */
  @Transactional
  public Reservation createReservationOptimisticBulk(List<Long> timeSlotIds) {

    List<TimeSlotVersioning> timeSlots = new ArrayList<>();
    LocalDateTime startTime = null;
    LocalDateTime endTime = null;
    for (var timeSlotId : timeSlotIds) {
      TimeSlotVersioning timeSlot =
          timeSlotVersioningRepository
              .findById(timeSlotId)
              .orElseThrow(
                  () -> new RuntimeException(String.format("TimeSlot %d not found", timeSlotId)));
      timeSlots.add(timeSlot);
      startTime = getStartTime(startTime, timeSlot.getStartTime());
      endTime = getEndTime(endTime, timeSlot.getEndTime());
    }

    int rowsUpdated = timeSlotVersioningRepository.bulkUpdateIsReservedTrue(timeSlotIds);
    if (rowsUpdated < timeSlotIds.size()) {
      throw new RuntimeException("Some TimeSlots failed to reserve due to optimistic locking");
    }

    Reservation reservation = new Reservation(null, startTime, endTime, 10000);
    reservationRepository.save(reservation);

    return reservation;
  }

  /**
   *
   *
   * <h1>낙관적 락 사용, 부분 수정으로 인해 경쟁 상태가 발생한다.</h1>
   *
   * @param timeSlotIds
   * @return
   */
  @Transactional
  public Reservation createReservationOptimistic(List<Long> timeSlotIds) {

    List<TimeSlotVersioning> timeSlots = new ArrayList<>();
    for (var timeSlotId : timeSlotIds) {
      TimeSlotVersioning timeSlot =
          timeSlotVersioningRepository
              .findById(timeSlotId)
              .orElseThrow(
                  () -> new RuntimeException(String.format("TimeSlot %d not found", timeSlotId)));
      timeSlots.add(timeSlot);
    }

    LocalDateTime startTime = null;
    LocalDateTime endTime = null;
    for (var timeSlot : timeSlots) {
      startTime = getStartTime(startTime, timeSlot.getStartTime());
      endTime = getEndTime(endTime, timeSlot.getEndTime());

      timeSlot.setIsReserved(true);
      int rows = timeSlotVersioningRepository.updateIsReservedTrue(timeSlot);
      if (rows == 0) {
        throw new RuntimeException("Optimistic reservation failed");
      }
    }
    Reservation reservation = new Reservation(null, startTime, endTime, 10000);
    reservationRepository.save(reservation);

    return reservation;
  }

  /**
   *
   *
   * <h1>락 미사용, 예약 생성 시 경쟁 조건이 발생하여 중복 예외 생성된다. </h1>
   *
   * @param timeSlotIds
   * @return
   */
  @Transactional
  public Reservation createReservationRaceCondition(List<Long> timeSlotIds) {

    LocalDateTime startTime = null;
    LocalDateTime endTime = null;
    for (var timeSlotId : timeSlotIds) {

      TimeSlot timeSlot = getTimeSlotRaceCondition(timeSlotId);

      startTime = getStartTime(startTime, timeSlot.getStartTime());
      endTime = getEndTime(endTime, timeSlot.getEndTime());

      timeSlot.setIsReserved(true);
      timeSlotRepository.updateIsReservedTrue(timeSlot);
    }

    Reservation reservation = new Reservation(null, startTime, endTime, 10000);
    reservationRepository.save(reservation);

    return reservation;
  }

  private LocalDateTime getStartTime(LocalDateTime originTime, LocalDateTime newTime) {
    if (originTime == null || newTime.isBefore(originTime)) {
      return newTime;
    }
    return originTime;
  }

  private LocalDateTime getEndTime(LocalDateTime originTime, LocalDateTime newTime) {
    if (originTime == null || newTime.isAfter(originTime)) {
      return newTime;
    }
    return originTime;
  }

  private TimeSlot getTimeSlotRaceCondition(Long timeSlotId) {
    TimeSlot timeSlot =
        timeSlotRepository
            .findById(timeSlotId)
            .orElseThrow(
                () -> new RuntimeException(String.format("TimeSlot %d not found", timeSlotId)));

    if (timeSlot.getIsReserved()) {
      throw new RuntimeException("TimeSlot is reserved");
    }
    return timeSlot;
  }
}
