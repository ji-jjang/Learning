package com.juny.batchaggregation.batch;

import com.juny.batchaggregation.entity.Order;
import com.juny.batchaggregation.entity.Refund;
import com.juny.batchaggregation.entity.Sale;
import com.juny.batchaggregation.repository.OrderRepository;
import com.juny.batchaggregation.repository.RefundRepository;
import com.juny.batchaggregation.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class salesAndRefundsJob {
//
//  private final JobRepository jobRepository;
//  private final PlatformTransactionManager platformTransactionManager;
//
//  private final OrderRepository orderRepository;
//  private final SaleRepository saleRepository;
//  private final RefundRepository refundRepository;
//
//  @Bean
//  public Job salesAndRefundsJob() {
//
//    return new JobBuilder("salesAndRefundsJob", jobRepository)
//      .start(processSalesStep())
//      .next(processRefundsStep())
//      .build();
//  }
//
//  @Bean
//  public Step processSalesStep() {
//
//    return new StepBuilder("processSalesStep", jobRepository)
//  }
//
//  @Bean
//  public Step processRefundsStep() {
//
//    return new StepBuilder("processRefundsStep", jobRepository)
//  }
//}
