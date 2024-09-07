package com.juny.batchaggregation.config;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchMetadataDBConfig {

  @Primary
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource-batch-metadata")
  public DataSource batchMetadataDBSource() {

    return DataSourceBuilder.create().build();
  }

  @Primary
  @Bean
  public PlatformTransactionManager metaTransactionManager() {

    return new DataSourceTransactionManager(batchMetadataDBSource());
  }
}
