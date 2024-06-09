package com.water.water;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class WaterApplicationTestConfiguration {

  @Bean
  public DataSource dataSource() {
    return DataSourceBuilder
        .create()
        .url("jdbc:h2:mem:testdb")
        .driverClassName("org.h2.Driver")
        .username("sa")
        .password("")
        .build();
  }
}
