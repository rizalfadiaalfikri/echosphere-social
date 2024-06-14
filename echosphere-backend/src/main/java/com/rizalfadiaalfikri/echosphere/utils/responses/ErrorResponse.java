package com.rizalfadiaalfikri.echosphere.utils.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse extends AbstractResponse {

  public ErrorResponse(int status, String message, String version) {
    this.setCode(status);
    this.setMessage(message);
    this.setSuccess(false);
    this.setVersion(version);
  }

}
