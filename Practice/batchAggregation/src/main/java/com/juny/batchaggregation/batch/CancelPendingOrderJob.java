package com.juny.batchaggregation.batch;

import com.juny.batchaggregation.entity.Order;
import com.juny.batchaggregation.entity.OrderStatus;
import com.juny.batchaggregation.repository.OrderRepository;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class CancelPendingOrderJob {

  private final JobRepository jobRepository;
  private final PlatformTransactionManager platformTransactionManager;
  private final OrderRepository orderRepository;

  @Bean
  public Job cancelPendingOrdersJob() {
    return new JobBuilder("cancelPendingOrdersJob", jobRepository)
      .start(cancelPendingOrdersStep())
      .build();
  }

  @Bean
  public Step cancelPendingOrdersStep() {
    return new StepBuilder("cancelPendingOrdersStep", jobRepository)
      .<Order, Order>chunk(10, platformTransactionManager)
      .reader(pendingOrdersReader())
      .processor(pendingOrdersProcessor())
      .writer(pendingOrdersWriter())
      .build();
  }

  @Bean
  public RepositoryItemReader<Order> pendingOrdersReader() {
    LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
    return new RepositoryItemReaderBuilder<Order>()
      .name("pendingOrdersReader")
      .repository(orderRepository)
      .methodName("findByStatusAndCreatedAtBefore")
      .arguments(OrderStatus.결제대기, oneMonthAgo)
      .pageSize(10)
      .sorts(Map.of("createdAt", Sort.Direction.ASC, "id", Sort.Direction.ASC)) // 다중 정렬 조건 추가
      .build();
  }

  @Bean
  public ItemProcessor<Order, Order> pendingOrdersProcessor() {
    return order -> {

      if (order.getCreatedAt().isBefore(LocalDateTime.now().minusDays(7))) {
        log.info("Processing order: {}", order);

        order.ChangeOrderStatus(OrderStatus.결제만료);
      }
      return order;
    };
  }

  @Bean
  public RepositoryItemWriter<Order> pendingOrdersWriter() {
    return new RepositoryItemWriterBuilder<Order>()
      .repository(orderRepository)
      .methodName("save")
      .build();
  }
}
