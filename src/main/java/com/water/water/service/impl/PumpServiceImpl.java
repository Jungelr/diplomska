package com.water.water.service.impl;

import com.water.water.model.PumpClaim;
import com.water.water.model.dtos.PumpAccessDto;
import com.water.water.repository.PumpClaimRepository;
import com.water.water.service.PumpClaimCounterService;
import com.water.water.service.PumpService;
import com.water.water.service.PumpSwitchService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.LongStream;

@Service
@RequiredArgsConstructor
public class PumpServiceImpl implements PumpService {

  private final PumpClaimRepository pumpClaimRepository;
  private final PumpClaimCounterService pumpClaimCounterService;
  private final PumpSwitchService pumpSwitchService;

  @PostConstruct
  void init() {
    pumpClaimRepository.deleteAll();
  }

  @Transactional
  public PumpAccessDto acquirePump(String id) {
    synchronized (pumpClaimCounterService) {
      if (pumpClaimRepository.findById(id).isPresent()) {
        return new PumpAccessDto(true);
      }

      if (pumpClaimCounterService.isClaimable()) {

        pumpClaimCounterService.incrementClaims();

        PumpClaim pumpClaim = new PumpClaim();
        pumpClaim.setPlantId(id);

        pumpClaimRepository.save(pumpClaim);

        pumpSwitchService.on();

        return new PumpAccessDto(true);
      }
    }

    return new PumpAccessDto(false);
  }

  @Override
  @Transactional
  public void releasePump(String id) {
    synchronized (pumpClaimCounterService) {
      pumpClaimRepository.findById(id).ifPresent((_) -> {
        pumpClaimRepository.deleteById(id);
        pumpClaimCounterService.decrementClaims();
      });

      if (pumpClaimCounterService.isClaimsCounterZero()) {
        pumpSwitchService.off();
      }
    }
  }

  @Override
  public PumpAccessDto hasClaim(String id) {

    return new PumpAccessDto(pumpClaimRepository.findById(id).isPresent());
  }

  @Scheduled(cron = "* */10 * * * *")
  @Transactional
  public void removeAbandonedClaims() {
    synchronized (pumpClaimCounterService) {
      long deletedClaims = pumpClaimRepository.deleteAllByLastModifiedBefore(LocalDateTime.now().minusMinutes(10));
      LongStream.range(0, deletedClaims).forEach((_) -> pumpClaimCounterService.decrementClaims());
    }
  }
}
