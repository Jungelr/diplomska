package com.water.water.configuration;

import com.pi4j.Pi4J;
import com.pi4j.io.gpio.digital.DigitalOutput;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
public class Pi4jConfiguration {

  private static final int PIN_PUMP = 7;

  @Bean
  @Profile("prod")
  public DigitalOutput pump() {
    return Pi4J.newAutoContext().digitalOutput().create(PIN_PUMP);
  }

  @Bean
  @Profile("!prod")
  public DigitalOutput pumpMock() {

    DigitalOutput digitalOutput = mock(DigitalOutput.class);
    when(digitalOutput.low()).then((_) -> {

      System.out.println("Low");
      return digitalOutput;
    });

    when(digitalOutput.high()).then((_) -> {

      System.out.println("High");
      return digitalOutput;
    });
    return digitalOutput;
  }

}