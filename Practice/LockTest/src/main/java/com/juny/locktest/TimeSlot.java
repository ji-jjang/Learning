package com.juny.locktest;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlot {

  private Long id;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private Boolean isReserved;
  private Integer price;
}
