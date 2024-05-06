package com.juny.core.ch05;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Bean
  public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
    return new HiddenHttpMethodFilter();
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {

      registry.addResourceHandler("/webjars/**")
          .addResourceLocations("/webjars/")
          .resourceChain(false);
  }
}
