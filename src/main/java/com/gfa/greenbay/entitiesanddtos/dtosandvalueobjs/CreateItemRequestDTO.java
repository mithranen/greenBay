package com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateItemRequestDTO {

  private String name;
  private String description;
  private String photoURL;
  private String bidEndingDateTime;
  private Integer bidStartingPrice;
}
