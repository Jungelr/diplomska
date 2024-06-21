package com.water.water.service.impl;

import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
import com.water.water.configuration.Pi4jContext;
import com.water.water.model.PumpClaim;
import com.water.water.model.dtos.PumpAccessDto;
import com.water.water.repository.PumpClaimRepository;
import com.water.water.service.PumpClaimCounterService;
import com.water.water.service.PumpService;
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
  private final Pi4jContext context;
  private DigitalOutput digitalOutput;

  @PostConstruct
  void init() {
    digitalOutput = context.getContext().digitalOutput().create(27);
    digitalOutput.addListener(System.out::println);
  }

  public PumpAccessDto acquirePump(String id) {
    digitalOutput.state(DigitalState.HIGH);
    synchronized (pumpClaimCounterService) {
      if (pumpClaimCounterService.isClaimable()) {

        pumpClaimCounterService.incrementClaims();

        PumpClaim pumpClaim = new PumpClaim();
        pumpClaim.setPlantId(id);

        pumpClaimRepository.save(pumpClaim);



        return new PumpAccessDto(true);
      }
    }

    return new PumpAccessDto(false);
  }

  @Override
  @Transactional
  public void releasePump(String id) {
    digitalOutput.state(DigitalState.LOW);
    synchronized (pumpClaimCounterService) {
      pumpClaimRepository.findById(id).ifPresent((_) -> {
        pumpClaimRepository.deleteById(id);
        pumpClaimCounterService.decrementClaims();
      });

      if (pumpClaimCounterService.isClaimsCounterZero()) {
        System.out.println("Xd");
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
