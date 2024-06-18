package com.water.water.repository;

import com.water.water.model.PumpClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PumpClaimRepository extends JpaRepository<PumpClaim, String> {

  long deleteAllByLastModifiedBefore(LocalDateTime localDateTime);
}
