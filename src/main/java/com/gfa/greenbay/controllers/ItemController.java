package com.gfa.greenbay.controllers;

import com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs.CreateItemRequestDTO;
import com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs.ItemResponseDTO;
import com.gfa.greenbay.services.item.ItemService;
import com.gfa.greenbay.utils.security.JwtUtilServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/greenbay/api")
public class ItemController {

  private final ItemService itemService;
  private final JwtUtilServiceImpl jwtUtilService;

  @Autowired
  public ItemController(
      ItemService itemService, JwtUtilServiceImpl jwtUtilService) {
    this.itemService = itemService;
    this.jwtUtilService = jwtUtilService;
  }

  @PostMapping("/item")
  public ResponseEntity<?> createNewItem(Authentication authentication,
      @RequestBody(required = false) CreateItemRequestDTO request) {
    String username = authentication.getName();
    ItemResponseDTO response = itemService.createNewItem(request, username);
    return ResponseEntity.ok().body(response);
  }

  //TODO pageable pageNUM
  @GetMapping(value = {"/items-to-sell", "/items-to-sell/{pageNum}"},
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> get20ItemsToSellByPage(Authentication authentication,
      @PathVariable(required = false) Integer pageNum)
  /*@RequestHeader("Authorization") String token*/ {
    //Long userId = jwtUtilService.getUserIdFromToken(token);
    String username = authentication.getName();
    ItemResponseDTO response = itemService.get20SellableItemsByPage(username, pageNum);
    return ResponseEntity.ok().body(response);
  }

  @GetMapping("/items-to-sell/all")
  public ResponseEntity<?> getAllItemsToSell(Authentication authentication) {
    String username = authentication.getName();
    ItemResponseDTO response = itemService.getAllSellableItems(username);
    return ResponseEntity.ok().body(response);
  }

  @GetMapping("/items-to-buy/{itemId}")
  public ResponseEntity<?> getItemToBuyById(Authentication authentication,
      @PathVariable Long itemId) {
    String username = authentication.getName();
    ItemResponseDTO response = itemService.getBuyableItemById(itemId, username);
    return ResponseEntity.ok().body(response);
  }


}
