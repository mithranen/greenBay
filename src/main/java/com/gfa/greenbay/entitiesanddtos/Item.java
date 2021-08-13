package com.gfa.greenbay.entitiesanddtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gfa.greenbay.entitiesanddtos.enums.Status;
import com.gfa.greenbay.utils.tools.time.LocalDateTimeDeserializer;
import com.gfa.greenbay.utils.tools.time.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  private String photoURL;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @JsonIgnore
  private User user;

  //TODO starting time for bid
  //private LocalDateTime bidStartedAt;

  //TODO global time formatter
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime bidEndingDateTime;

  private Integer bidStartingPrice;
  private Integer lastBidOffer;
  private Integer purchasePrice;
  private String buyerName;
  private String sellerName;

  //@Transient
  //@ElementCollection
  //private List<Integer> bidderNameList;

  @Enumerated(EnumType.STRING)
  private Status status;


}
