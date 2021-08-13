package com.gfa.greenbay.services.item;

import com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs.CreateItemRequestDTO;
import com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs.ItemResponseDTO;

public interface ItemService {

  ItemResponseDTO createNewItem(CreateItemRequestDTO request, String username);

  ItemResponseDTO get20SellableItemsByPage(String username, Integer pageNum);

  ItemResponseDTO getAllSellableItems(String username);

  ItemResponseDTO getBuyableItemById(Long itemId, String username);
}
