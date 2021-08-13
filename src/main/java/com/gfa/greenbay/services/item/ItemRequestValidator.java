package com.gfa.greenbay.services.item;

import com.gfa.greenbay.entitiesanddtos.Item;
import com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs.CreateItemRequestDTO;

public interface ItemRequestValidator {

  void validateCreateItemRequestDTO(CreateItemRequestDTO request);

  void validateGetItemByPageRequest(Integer pageNum);

  void validateItemByIdNotBelongsToUser(Item item, String username);
}
