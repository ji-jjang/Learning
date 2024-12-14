package com.juny.locktest;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationRepository {

  void save(Reservation reservation);
}
