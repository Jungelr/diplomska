package com.water.water.service.impl;

import com.pi4j.io.gpio.digital.DigitalOutput;
import com.water.water.model.PumpClaim;
import com.water.water.model.dtos.PumpAccessDto;
import com.water.water.repository.PumpClaimRepository;
import com.water.water.service.PumpClaimCounterService;
import com.water.water.service.PumpService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.LongStream;

@Service
@RequiredArgsConstructor
public class PumpServiceImpl implements PumpService {

  private final PumpClaimRepository pumpClaimRepository;

  private final PumpClaimCounterService pumpClaimCounterService;
  private final DigitalOutput pump;

  @Override
  public PumpAccessDto acquirePump(String id) {
    synchronized (pumpClaimCounterService) {
      if (pumpClaimCounterService.isClaimable()) {

        pumpClaimCounterService.incrementClaims();

        PumpClaim pumpClaim = new PumpClaim();
        pumpClaim.setPlantId(id);

        pumpClaimRepository.save(pumpClaim);

        pump.high();

        return new PumpAccessDto(true);
      }
    }

    return new PumpAccessDto(false);
  }

  @Override
  public void releasePump(String id) {
    synchronized (pumpClaimCounterService) {
      pumpClaimRepository.deleteById(id);
      pumpClaimCounterService.decrementClaims();

      if (pumpClaimCounterService.isClaimsCounterZero()) {
        pump.low();
      }
    }
  }

  @Override
  public PumpAccessDto hasClaim(String id) {

    return new PumpAccessDto(pumpClaimRepository.findById(id).isPresent());
  }

  @Scheduled(cron = "* */10 * * * *")
  public void removeAbandonedClaims() {
    synchronized (pumpClaimCounterService) {
      long deletedClaims = pumpClaimRepository.deleteAllByLastModifiedBefore(LocalDateTime.now().minusMinutes(10));
      LongStream.range(0, deletedClaims).forEach((_) -> pumpClaimCounterService.decrementClaims());
    }
  }
}
