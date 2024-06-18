package com.water.water.service;

import com.water.water.model.dtos.PumpAccessDto;

public interface PumpService {

  PumpAccessDto acquirePump(String id);

  void releasePump(String id);

  PumpAccessDto hasClaim(String id);
}
