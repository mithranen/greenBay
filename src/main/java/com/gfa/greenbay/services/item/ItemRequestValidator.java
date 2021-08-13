package com.gfa.greenbay.services.item;

import com.gfa.greenbay.entitiesanddtos.CreateItemRequestDTO;

public interface ItemRequestValidator {

  void validateCreateItemRequestDTO(CreateItemRequestDTO request);
}
