package com.juny.batchaggregation.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AggregationScheduler {

  private final JobLauncher jobLauncher;
  private final JobRegistry jobRegistry;

//  @Scheduled(cron = "0 0 2 15 * *", zone = "Asia/Seoul")
  public void getSalesAndRefunds() throws Exception {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
    String date = dateFormat.format(new Date());

    JobParameters jobParameters = new JobParametersBuilder()
      .addString("date", date)
      .toJobParameters();

    jobLauncher.run(jobRegistry.getJob("salesAndRefundsJob"), jobParameters);
  }
}
