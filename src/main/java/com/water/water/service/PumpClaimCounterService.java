package com.water.water.service;

public interface PumpClaimCounterService {

  boolean isClaimable();

  void incrementClaims();
  void decrementClaims();

  boolean isClaimsCounterZero();
}
