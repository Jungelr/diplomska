package com.water.water.service;

import org.springframework.core.io.Resource;

public interface UpdateService {

  void uploadUpdate(Resource updateDto);

  Resource getLatestUpdate();

  String getLatestUpdateHash();
}
