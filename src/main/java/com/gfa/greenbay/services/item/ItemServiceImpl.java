package com.gfa.greenbay.services.item;

import com.gfa.greenbay.entitiesanddtos.Item;
import com.gfa.greenbay.entitiesanddtos.User;
import com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs.CreateItemRequestDTO;
import com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs.ItemResponseDTO;
import com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs.ItemResponseOverview;
import com.gfa.greenbay.entitiesanddtos.enums.Status;
import com.gfa.greenbay.repositories.ItemRepository;
import com.gfa.greenbay.services.UserService;
import com.gfa.greenbay.utils.tools.Toolbox;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

  private final UserService userService;
  private final ItemRepository itemRepository;
  private final ItemRequestValidator itemRequestValidator;

  @Autowired
  public ItemServiceImpl(UserService userService,
      ItemRepository itemRepository,
      ItemRequestValidator itemRequestValidator) {
    this.userService = userService;
    this.itemRepository = itemRepository;
    this.itemRequestValidator = itemRequestValidator;
  }

  @Override
  public ItemResponseDTO createNewItem(CreateItemRequestDTO request, String username) {
    itemRequestValidator.validateCreateItemRequestDTO(request);
    User currentUser = userService.findUserByUserName(username);
    Item newItem = convertRequestToItem(request);

    newItem.setBidEndingDateTime(
        Toolbox.convertRequestTimeToLocalDAteTime(request.getBidEndingDateTime()));
    newItem.setStatus(Status.ACTIVE);
    newItem.setUser(currentUser);
    newItem.setSellerName(currentUser.getUsername());
    itemRepository.save(newItem);
    return convertItemToResponse(newItem);
  }

  //region private methods for createNewItem()
  private Item convertRequestToItem(CreateItemRequestDTO request) {
    return Toolbox.modelmapper().map(request, Item.class);
  }

  private ItemResponseDTO convertItemToResponse(Item newItem) {
    return Toolbox.modelmapper().map(newItem, ItemResponseDTO.class);
  }  //endregion

  @Override
  public ItemResponseDTO get20SellableItemsByPage(String username, Integer pageNum) {
    User currentUser = userService.findUserByUserName(username);
    Long userId = currentUser.getId();
    List<Item> itemList;

    if (pageNum == null) {
      itemList = findFirst20ByPageNum(0, userId);
      return convertItemListToResponseDTO(itemList);
    }
    itemRequestValidator.validateGetItemByPageRequest(pageNum);
    if (pageNum >= 0) {
      itemList = findFirst20ByPageNum(pageNum, userId);
      return convertItemListToResponseDTO(itemList);
    }
    return null;
  }

  @Override
  public ItemResponseDTO getAllSellableItems(String username) {
    User currentUser = userService.findUserByUserName(username);
    List<Item> itemList = getActiveItems(currentUser);
    return convertItemListToResponseDTO(itemList);
  }

  //region private methods for findFirst20ByPageNum() AND getAllItemsToSell()
  private List<Item> findFirst20ByPageNum(Integer pageNum, Long userId) {
    return itemRepository.findFirst20ItemByPageNum(PageRequest.of(pageNum, 20), userId);
  }

  private ItemResponseDTO convertItemListToResponseDTO(List<Item> items) {
    return new ItemResponseDTO(items.stream()
        .map(item -> Toolbox.modelmapper().map(item, ItemResponseOverview.class))
        .collect(Collectors.toList()));
  }

  private List<Item> getActiveItems(User currentUser) {
    return currentUser.getItems().stream()
        .filter(item -> item.getStatus().equals(Status.ACTIVE))
        .collect(Collectors.toList());
  } //endregion

  //  private List<Item> getItemsById(Long userId) {
  //    return StreamSupport.stream(itemRepository.findAll().spliterator(), false)
  //        .filter(item -> item.getId().equals(userId))
  //        .collect(Collectors.toList());
  //  }

  //TODO if sellable or not
  @Override
  public ItemResponseDTO getBuyableItemById(Long itemId, String username) {
    Item buyableItem = itemRepository.findById(itemId).orElseThrow(NoSuchElementException::new);
    itemRequestValidator.validateItemByIdNotBelongsToUser(buyableItem, username);
    if (buyableItem.getStatus() == Status.ACTIVE) {
      ItemResponseOverview valueObj = Toolbox.modelmapper()
          .map(buyableItem, ItemResponseOverview.class);
      return new ItemResponseDTO(valueObj);

    }
    return null;
  }
}
