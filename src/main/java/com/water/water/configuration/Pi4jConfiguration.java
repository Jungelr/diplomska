package com.water.water.configuration;

import com.pi4j.Pi4J;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
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
  public Pi4jContext createContext() {
    return new Pi4jContext(Pi4J.newAutoContext());
  }


  @Bean
  @Profile("prod")
  public DigitalOutput pump(Pi4jContext context) {
    DigitalOutput output = context.getContext().dout().create(PIN_PUMP);
    output.config().shutdownState(DigitalState.LOW);
    output.addListener(System.out::println);

    return output;
  }

  @Bean
  @Profile("prod")
  public DigitalInput test(Pi4jContext context) {
    return context.getContext().digitalInput().create(11);
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