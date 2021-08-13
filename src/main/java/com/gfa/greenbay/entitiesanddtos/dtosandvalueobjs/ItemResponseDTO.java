package com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gfa.greenbay.entitiesanddtos.enums.Status;
import com.gfa.greenbay.utils.tools.time.LocalDateTimeDeserializer;
import com.gfa.greenbay.utils.tools.time.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ItemResponseDTO {

  private Long id;
  private String name;
  private String description;
  private String photoURL;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime bidEndingDateTime;
  private Integer bidStartingPrice;
  private Integer purchasePrice;
  private Status status;

  private List<ItemResponseOverview> itemsToSell;
  private ItemResponseOverview itemsToBuy;

  public ItemResponseDTO(List<ItemResponseOverview> itemsToSell) {
    this.itemsToSell = itemsToSell;
  }

  public ItemResponseDTO(ItemResponseOverview itemsToBuy) {
    this.itemsToBuy = itemsToBuy;
  }
}
