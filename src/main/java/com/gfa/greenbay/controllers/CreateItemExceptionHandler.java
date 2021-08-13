package com.gfa.greenbay.controllers;

import com.gfa.greenbay.entitiesanddtos.ErrorDTO;
import com.gfa.greenbay.exceptions.item.CreateItemInvalidBidTimeException;
import com.gfa.greenbay.exceptions.item.CreateItemInvalidPhotoUrlException;
import com.gfa.greenbay.exceptions.item.CreateItemInvalidStartingPriceException;
import com.gfa.greenbay.exceptions.item.CreateItemInvalidTimeFormatException;
import com.gfa.greenbay.exceptions.item.CreateItemMissingBidEndingTimeException;
import com.gfa.greenbay.exceptions.item.CreateItemMissingBidStartingPriceException;
import com.gfa.greenbay.exceptions.item.CreateItemMissingDescriptionException;
import com.gfa.greenbay.exceptions.item.CreateItemMissingNameException;
import com.gfa.greenbay.exceptions.item.CreateItemMissingPhotoUrlException;
import com.gfa.greenbay.exceptions.item.CreateItemRequestNullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CreateItemExceptionHandler {

  @ExceptionHandler(value = CreateItemRequestNullException.class)
  public ResponseEntity<?> handleNullRequest() {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorDTO(
            "Missing values: name, description, photo url, bid ending time, bid starting price!"));
  }

  @ExceptionHandler(value = CreateItemMissingNameException.class)
  public ResponseEntity<?> handleRequestHasNoName() {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorDTO("You did not give: name!"));

  }

  @ExceptionHandler(value = CreateItemMissingDescriptionException.class)
  public ResponseEntity<?> handleRequestHasNoDescription() {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorDTO("You did not give: description!"));
  }

  @ExceptionHandler(value = CreateItemMissingPhotoUrlException.class)
  public ResponseEntity<?> handleRequestHasNoPhoto() {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorDTO("You did not upload picture about item to sell!"));
  }

  @ExceptionHandler(value = CreateItemMissingBidEndingTimeException.class)
  public ResponseEntity<?> handleRequestHasNoBidEndingTime() {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorDTO("You did not give: bid ending time!"));
  }

  @ExceptionHandler(value = CreateItemMissingBidStartingPriceException.class)
  public ResponseEntity<?> handleRequestHasNoBidStartingPrice() {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorDTO("You did not give: bid starting price!"));
  }

  @ExceptionHandler(value = CreateItemInvalidPhotoUrlException.class)
  public ResponseEntity<?> handleRequestHasNoValidPhotoUrl() {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ErrorDTO("Photo url is invalid, or it is not image URL!"));
  }

  @ExceptionHandler(value = CreateItemInvalidStartingPriceException.class)
  public ResponseEntity<?> handleBidStartingPriceNotPositiveNumber() {
    return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(new ErrorDTO("Price should be positive number!"));
  }

  @ExceptionHandler(value = CreateItemInvalidTimeFormatException.class)
  public ResponseEntity<?> handleInvalidBidEndingTime() {
    return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(new ErrorDTO(
            "Bid ending time has invalid format or value. Use valid date in format: yyyy-MM-dd HH:mm!"));
  }

  @ExceptionHandler(value = CreateItemInvalidBidTimeException.class)
  public ResponseEntity<?> handleInvalidBidTime() {
    return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(new ErrorDTO(
            "Bid time must be at least 7 days from creation time. Bid ending time cannot be in the past!"));
  }
}
