package com.water.water.service.impl;

import com.water.water.repository.PumpClaimRepository;
import com.water.water.service.PumpClaimCounterService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PumpClaimCounterServiceImpl implements PumpClaimCounterService {

  private final PumpClaimRepository pumpClaimRepository;

  private static final long MAXIMUM_CLAIMS = 3;

  private long currentClaims = 0;

  @PostConstruct
  public void init() {
    currentClaims = pumpClaimRepository.count();
  }

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
