package com.juny.batchtest.domain.others;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
  // 주소 정보
  private String zipcode;
  private String streetAddress;
  private String detailAddress;
}

