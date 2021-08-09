package com.gfa.greenbay.services;

import com.gfa.greenbay.entitiesanddtos.LoginRequestDTO;
import com.gfa.greenbay.exceptions.IncorrectPasswordException;
import com.gfa.greenbay.exceptions.LoginRequestNullException;
import com.gfa.greenbay.exceptions.UsernameNotFoundException;
import com.gfa.greenbay.exceptions.UsernameOrPasswordIsEmptyException;
import com.gfa.greenbay.exceptions.UsernameOrPasswordMissingException;
import com.gfa.greenbay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginRequestValidatorImpl implements LoginRequestValidator {

  private final UserRepository userRepository;

  @Autowired
  public LoginRequestValidatorImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void validateLoginRequestDTO(LoginRequestDTO request) {
    validateLoginRequestNotNull(request);
    validateLoginRequestHasUsernameAndPassword(request);
    validateLoginRequestHasValues(request);
    validateUsernameExists(request);
    validatePasswordIsCorrect(request);
  }

  //region private validator methods
  private void validateLoginRequestNotNull(LoginRequestDTO request) {
    if (request == null) {
      throw new LoginRequestNullException();
    }
  }

  private void validateLoginRequestHasUsernameAndPassword(LoginRequestDTO request) {
    if (request.getPassword() == null || request.getUsername() == null) {
      throw new UsernameOrPasswordMissingException();
    }
  }

  private void validateLoginRequestHasValues(LoginRequestDTO request) {
    if (request.getPassword().equals("") || request.getUsername().equals("")) {
      throw new UsernameOrPasswordIsEmptyException();
    }
  }

  private void validateUsernameExists(LoginRequestDTO request) {
    if (userRepository.findUserByUsername(request.getUsername()).isEmpty()) {
      throw new UsernameNotFoundException();
    }
  }

  private void validatePasswordIsCorrect(LoginRequestDTO request) {
    String storedPwd = userRepository.findUserByUsername(request.getUsername()).get().getPassword();
    if (!storedPwd.equals(request.getPassword())) {
      throw new IncorrectPasswordException();
    }
  }
} //endregion private validator methods

