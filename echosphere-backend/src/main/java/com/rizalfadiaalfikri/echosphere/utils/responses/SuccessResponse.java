package com.rizalfadiaalfikri.echosphere.utils.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse extends AbstractResponse {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Object data;

  @Override
  public String toString() {
    return "{code: " + getCode() + ", message: " + getMessage()
        + ", success: " + isSuccess() + ", version: " + getVersion() + ", data: " + data + "}";
  }

}
