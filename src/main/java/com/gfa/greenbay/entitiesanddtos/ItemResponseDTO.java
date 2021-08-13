package com.gfa.greenbay.entitiesanddtos;

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

  private List<Item> itemsToSell;

  public ItemResponseDTO(List<Item> itemsToSell) {
    this.itemsToSell = itemsToSell;
  }
}
