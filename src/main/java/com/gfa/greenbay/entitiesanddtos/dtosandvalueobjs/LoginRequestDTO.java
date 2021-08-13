package com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {

  private String username;
  private String password;
}
