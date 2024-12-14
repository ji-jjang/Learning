package com.juny.locktest;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TimeSlotVersioningRepository {

  Optional<TimeSlotVersioning> findById(Long timeSlotId);

  int updateIsReservedTrue(TimeSlotVersioning timeSlot);

  int bulkUpdateIsReservedTrue(List<Long> timeSlotIds);
}
