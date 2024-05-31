package com.juny.batchtest.domain.monitoring.sale;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SaleController {

  private final SaleService saleService;

  @GetMapping("/api/v1/sales")
  public ResponseEntity<ResSale> getSales(@RequestParam String timeUnit, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime start, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime end) {
    ResSale sales = saleService.getSales(timeUnit, start, end);

    return new ResponseEntity<>(sales, HttpStatus.OK);
  }
}
