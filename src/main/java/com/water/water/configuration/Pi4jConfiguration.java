package com.water.water.configuration;

import com.pi4j.Pi4J;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class Pi4jConfiguration {

  @Bean
  @Profile("prod")
  public Pi4jContext createContext() {
    return new Pi4jContext(Pi4J.newAutoContext());
  }

  @Bean
  @Profile("!prod")
  public Pi4jContext pumpMock() {

    return new Pi4jContext(null);
  }
}