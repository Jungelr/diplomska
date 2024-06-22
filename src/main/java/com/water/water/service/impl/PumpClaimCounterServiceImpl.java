package com.water.water.service.impl;

import com.water.water.service.PumpClaimCounterService;
import org.springframework.stereotype.Service;

@Service
public class PumpClaimCounterServiceImpl implements PumpClaimCounterService {

  private static final long MAXIMUM_CLAIMS = 3;

  private long currentClaims = 0;

  @Override
  public boolean isClaimable() {
    return currentClaims < MAXIMUM_CLAIMS;
  }

  @Override
  public void incrementClaims() {
    currentClaims++;
  }

  @Override
  public void decrementClaims() {
    if (currentClaims > 0) {
      currentClaims--;
    }
  }

  @Override
  public boolean isClaimsCounterZero() {
    return currentClaims == 0;
  }
}
