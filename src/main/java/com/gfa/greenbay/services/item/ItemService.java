package com.gfa.greenbay.services.item;

import com.gfa.greenbay.entitiesanddtos.CreateItemRequestDTO;
import com.gfa.greenbay.entitiesanddtos.ItemResponseDTO;

public interface ItemService {

  ItemResponseDTO createNewItem(CreateItemRequestDTO request);

  ItemResponseDTO getItemsToSell(Long userId);
}
