package com.rizalfadiaalfikri.echosphere.utils.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractResponse {

  private int code;

  private String message;

  private boolean success = true;

  private String version;

}
