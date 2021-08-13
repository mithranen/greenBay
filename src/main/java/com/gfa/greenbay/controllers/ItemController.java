package com.gfa.greenbay.controllers;

import com.gfa.greenbay.entitiesanddtos.CreateItemRequestDTO;
import com.gfa.greenbay.entitiesanddtos.ItemResponseDTO;
import com.gfa.greenbay.services.item.ItemService;
import com.gfa.greenbay.utils.security.JwtUtilServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    ItemResponseDTO response = itemService.createNewItem(request);
    return ResponseEntity.ok().body(response);
  }

  //TODO pageable pageNUM
  @GetMapping("/items-to-sell/{pageNum}")
  public ResponseEntity<?> getItemsToSell(Authentication authentication,
      @PathVariable(required = false) int pageNum, @RequestHeader("Authorization") String token) {
    Long userId = jwtUtilService.getUserIdFromToken(token);
    //String username = authentication.getName();
    ItemResponseDTO response = itemService.getItemsToSell(userId);
    return ResponseEntity.ok().body(response);
  }
}
