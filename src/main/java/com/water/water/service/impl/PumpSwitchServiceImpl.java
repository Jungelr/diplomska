package com.water.water.service.impl;

import com.water.water.service.PumpSwitchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PumpSwitchServiceImpl implements PumpSwitchService {

  private final static String PUMP_URL = "http://pump:10080";
  private final static String PUMP_ON_URL = String.format("%s/on", PUMP_URL);
  private final static String PUMP_OFF_URL = String.format("%s/off", PUMP_URL);
  private final RestTemplate restTemplate;

  @Override
  public void on() {
    restTemplate.postForObject(PUMP_ON_URL,"", String.class);
  }

  @Override
  public void off() {
    restTemplate.postForObject(PUMP_OFF_URL,"", String.class);
  }
}
