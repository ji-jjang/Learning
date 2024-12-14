package com.juny.locktest;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TimeSlotService {

  private final TimeSlotRepository timeSlotRepository;

  public List<TimeSlot> getTimeSlot(LocalDate localDate) {
    List<TimeSlot> timeSlots = timeSlotRepository.findAllByDate(localDate);
    return timeSlots;
  }

  @Transactional(isolation = Isolation.READ_UNCOMMITTED)
  public List<TimeSlot> getTimeSlotUnCommited(LocalDate localDate) {
    List<TimeSlot> timeSlots = timeSlotRepository.findAllByDate(localDate);
    return timeSlots;
  }

  @Transactional
  public List<TimeSlot> getTimeSlotForShare(LocalDate localDate) {
    List<TimeSlot> timeSlots = timeSlotRepository.findAllByDateForShare(localDate);
    return timeSlots;
  }

  @Transactional(isolation = Isolation.READ_UNCOMMITTED)
  public List<TimeSlot> getTimeSlotForShareAndReadUnCommited(LocalDate localDate) {
    List<TimeSlot> timeSlots = timeSlotRepository.findAllByDateForShare(localDate);
    return timeSlots;
  }
}
