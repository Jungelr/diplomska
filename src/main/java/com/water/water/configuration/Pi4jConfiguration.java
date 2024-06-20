package com.water.water.configuration;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalInputProvider;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    Context context = mock(Context.class);
    DigitalOutputProvider digitalOutputProvider = mock(DigitalOutputProvider.class);
    when(context.digitalOutput()).thenReturn(digitalOutputProvider);
    DigitalOutput digitalOutput = mock(DigitalOutput.class);
    when(digitalOutputProvider.create(7)).thenReturn(digitalOutput);
    when(digitalOutput.low()).thenReturn(digitalOutput);

    DigitalInputProvider digitalInputProvider = mock(DigitalInputProvider.class);
    when(context.digitalInput()).thenReturn(digitalInputProvider);
    DigitalInput digitalInput = mock(DigitalInput.class);
    when(digitalInputProvider.create(7)).thenReturn(digitalInput);

    return new Pi4jContext(context);
  }
}