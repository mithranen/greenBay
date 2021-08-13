package com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

  private String jwToken;
  private Integer money;
}
