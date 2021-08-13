package com.gfa.greenbay.entitiesanddtos.enums;

import lombok.Getter;

@Getter
public enum Status {
  ACTIVE("Active"),
  SOLD_OUT("Sold out!");

  public String status;

  Status(String status) {
    this.status = status;
  }
}
