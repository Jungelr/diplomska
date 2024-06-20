package com.water.water.configuration;

import com.pi4j.context.Context;
import jakarta.annotation.PreDestroy;
import lombok.Getter;

@Getter
public class Pi4jContext {

  private final Context context;

  public Pi4jContext(Context context) {
    this.context = context;
  }

  @PreDestroy
  public void destroy() {
    this.context.shutdown();
  }
}
