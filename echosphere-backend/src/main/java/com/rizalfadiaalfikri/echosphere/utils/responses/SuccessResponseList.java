package com.rizalfadiaalfikri.echosphere.utils.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponseList extends AbstractResponse {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("data")
  private List details;

}
