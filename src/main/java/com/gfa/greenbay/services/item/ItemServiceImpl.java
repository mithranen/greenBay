package com.gfa.greenbay.services.item;

import com.gfa.greenbay.entitiesanddtos.CreateItemRequestDTO;
import com.gfa.greenbay.entitiesanddtos.Item;
import com.gfa.greenbay.entitiesanddtos.ItemResponseDTO;
import com.gfa.greenbay.entitiesanddtos.enums.Status;
import com.gfa.greenbay.repositories.ItemRepository;
import com.gfa.greenbay.utils.tools.Toolbox;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

  private final ItemRepository itemRepository;
  private final ItemRequestValidator itemRequestValidator;

  @Autowired
  public ItemServiceImpl(ItemRepository itemRepository,
      ItemRequestValidator itemRequestValidator) {
    this.itemRepository = itemRepository;
    this.itemRequestValidator = itemRequestValidator;
  }

  @Override
  public ItemResponseDTO createNewItem(CreateItemRequestDTO request) {
    itemRequestValidator.validateCreateItemRequestDTO(request);
    Item newItem = convertRequestToItem(request);
    newItem.setBidEndingDateTime(
        Toolbox.convertRequestTimeToLocalDAteTime(request.getBidEndingDateTime()));
    newItem.setStatus(Status.ACTIVE);
    itemRepository.save(newItem);
    return convertItemToResponse(newItem);
  }

  //region private methods for createNewItem()
  private Item convertRequestToItem(CreateItemRequestDTO request) {
    return Toolbox.modelmapper().map(request, Item.class);
  }

  private ItemResponseDTO convertItemToResponse(Item newItem) {
    return Toolbox.modelmapper().map(newItem, ItemResponseDTO.class);
  }  //endregion private methods for createNewItem()

  @Override
  public ItemResponseDTO getItemsToSell(Long userI) {
    List<Item> itemList = getItemsById(userI);
    return convertItemListToResponseDTO(itemList);
  }

  private List<Item> getItemsById(Long userId) {
    return StreamSupport.stream(itemRepository.findAll().spliterator(), false)
        .filter(item -> item.getId().equals(userId))
        .collect(Collectors.toList());
  }

  private ItemResponseDTO convertItemListToResponseDTO(List<Item> items) {
    return new ItemResponseDTO(items);
  }

}
