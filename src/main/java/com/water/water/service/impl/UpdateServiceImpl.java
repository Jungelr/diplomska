package com.water.water.service.impl;

import com.water.water.model.Update;
import com.water.water.repository.UpdateRepository;
import com.water.water.service.UpdateService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.util.Base64;

@RequiredArgsConstructor
@Service
public class UpdateServiceImpl implements UpdateService {

  private final UpdateRepository updateRepository;

  @Override
  @SneakyThrows
  @Transactional
  public void uploadUpdate(Resource updateDto) {

    MessageDigest digest = MessageDigest.getInstance("SHA-256");

    byte[] file = Base64.getEncoder().encode(updateDto.getContentAsByteArray());

    Update update = new Update();
    update.setFilename(updateDto.getFilename());
    update.setUpdate(file);
    update.setHash(new String(digest.digest(file)));

    updateRepository.deleteAll();
    updateRepository.saveAndFlush(update);
  }

  @Override
  public Resource getLatestUpdate() {
    Update latestUpdate =  updateRepository
        .findAll()
        .stream()
        .findFirst()
        .orElseThrow();

    byte[] file = Base64.getDecoder().decode(latestUpdate.getUpdate());

    return new ByteArrayResource(file, latestUpdate.getFilename());
  }

  @Override
  public String getLatestUpdateHash() {
    return updateRepository
        .findAll()
        .stream()
        .findFirst()
        .orElseThrow()
        .getHash();
  }
}
