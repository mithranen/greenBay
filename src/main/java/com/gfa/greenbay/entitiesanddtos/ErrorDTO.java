package com.gfa.greenbay.entitiesanddtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorDTO {

  private String errorMessage;

  public ErrorDTO(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
