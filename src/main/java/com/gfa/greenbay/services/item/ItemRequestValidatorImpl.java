package com.gfa.greenbay.services.item;

import com.gfa.greenbay.entitiesanddtos.Item;
import com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs.CreateItemRequestDTO;
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
import com.gfa.greenbay.exceptions.item.InvalidPageNumberException;
import com.gfa.greenbay.utils.tools.Toolbox;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.imageio.ImageIO;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;

@Service
public class ItemRequestValidatorImpl implements ItemRequestValidator {

  @Override
  public void validateCreateItemRequestDTO(CreateItemRequestDTO request) {
    validateRequestNotNull(request);
    validateRequestHasName(request);
    validateRequestHasDescription(request);
    validateRequestHasPhotoUrl(request);
    validateRequestHasBidEndingTime(request);
    validateRequestHasBidStartingPrice(request);
    validateRequestHasCorrectPhotoUrl(request);
    validateRequestPhotoUrlIsImage(request);
    validateStartingPriceIsPositiveWholeNumber(request);
    validateBidEndingDateTimeFormat(request);
    validateBidEndingDateTime(request);
  }

  //region private validator methods for CREATE item
  private void validateRequestNotNull(CreateItemRequestDTO request) {
    if (request == null) {
      throw new CreateItemRequestNullException();
    }
  }

  private void validateRequestHasName(CreateItemRequestDTO request) {
    if (request.getName() == null || request.getName().equals("")) {
      throw new CreateItemMissingNameException();
    }
  }

  private void validateRequestHasDescription(CreateItemRequestDTO request) {
    if (request.getDescription() == null || request.getDescription().equals("")) {
      throw new CreateItemMissingDescriptionException();
    }
  }

  private void validateRequestHasPhotoUrl(CreateItemRequestDTO request) {
    if (request.getPhotoURL() == null || request.getPhotoURL().equals("")) {
      throw new CreateItemMissingPhotoUrlException();
    }
  }

  private void validateRequestHasBidEndingTime(CreateItemRequestDTO request) {
    if (request.getBidEndingDateTime() == null || request.getBidEndingDateTime().equals("")) {
      throw new CreateItemMissingBidEndingTimeException();
    }
  }

  private void validateRequestHasBidStartingPrice(CreateItemRequestDTO request) {
    if (request.getBidStartingPrice() == null) {
      throw new CreateItemMissingBidStartingPriceException();
    }
  }

  private void validateRequestHasCorrectPhotoUrl(CreateItemRequestDTO request) {
    String photoUrl = request.getPhotoURL();
    UrlValidator urlValidator = new UrlValidator();
    if (!urlValidator.isValid(photoUrl)) {
      throw new CreateItemInvalidPhotoUrlException();
    }
  }

  private void validateRequestPhotoUrlIsImage(CreateItemRequestDTO request) {
    BufferedImage image = null;
    try {
      image = ImageIO.read(new URL(request.getPhotoURL()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (image == null) {
      throw new CreateItemInvalidPhotoUrlException();
    }
  }

  //TODO &&... whole num validate not working --> here is already change double to integer
  private void validateStartingPriceIsPositiveWholeNumber(CreateItemRequestDTO request) {
    if (!(request.getBidStartingPrice() >= 0)
        || request.getBidStartingPrice().toString().contains("\\.")) {
      throw new CreateItemInvalidStartingPriceException();
    }
  }

  private void validateBidEndingDateTimeFormat(CreateItemRequestDTO request) {
    String requestBidEndingTime = request.getBidEndingDateTime();
    if (!requestBidEndingTime.matches(
        "\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[01])\\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])")) {
      throw new CreateItemInvalidTimeFormatException();
    }

  }

  private void validateBidEndingDateTime(CreateItemRequestDTO request) {
    LocalDateTime time = Toolbox.convertRequestTimeToLocalDAteTime(request.getBidEndingDateTime());

    ZoneId zoneId = ZoneId.systemDefault();
    long startingTimeInS = time.atZone(zoneId).toEpochSecond();
    long actualTimeInS = LocalDateTime.now().atZone(zoneId).toEpochSecond();
    long oneDayInS = 86400L;
    if (startingTimeInS < actualTimeInS + 7 * oneDayInS) {
      throw new CreateItemInvalidBidTimeException();
    }
  } //endregion private validator methods for CREATE item

  //TODO validating whole number --> should nums receiving in Str or double to validate?
  @Override
  public void validateGetItemByPageRequest(Integer pageNum) {
    if (pageNum < 0 || pageNum.toString().contains(".")) {
      throw new InvalidPageNumberException();
    }
  }

  @Override
  public void validateItemByIdNotBelongsToUser(Item item, String username) {
    if (item.getUser().getUsername().equals(username)) {
      throw new IllegalArgumentException();
    }
  }

  //  @Override
  //  public void validateItemByIdNotBelongsToUser(Long itemId, User currentUser) {
  //    List<Long> listOfIdS = currentUser.getItems().stream().map(Item::getId).collect(
  //        Collectors.toList());
  //    if (listOfIdS.contains(itemId)) {
  //      throw new IllegalArgumentException();
  //    }
  //  }
}

