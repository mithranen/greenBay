package com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs;

import com.gfa.greenbay.entitiesanddtos.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponseOverview {

  private String name;
  private String photoURL;
  private Integer lastBidOffer;
  private Status status;
}
