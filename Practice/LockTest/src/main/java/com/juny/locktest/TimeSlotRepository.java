package com.juny.locktest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TimeSlotRepository {

  List<TimeSlot> findAllByDate(LocalDate date);

  Optional<TimeSlot> findById(Long timeSlotId);

  void updateIsReservedTrue(TimeSlot timeSlot);

  List<TimeSlot> findByIdsForUpdate(List<Long> timeSlotIds);

  List<TimeSlot> findByIdsForShare(List<Long> timeSlotIds);

  List<TimeSlot> findAllByDateForShare(LocalDate localDate);
}
