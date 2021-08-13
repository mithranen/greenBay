package com.gfa.greenbay.services.login;

import com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs.LoginRequestDTO;
import com.gfa.greenbay.exceptions.login.IncorrectUserNameOrPassword;
import com.gfa.greenbay.exceptions.login.LoginRequestNullException;
import com.gfa.greenbay.exceptions.login.PasswordMissingException;
import com.gfa.greenbay.exceptions.login.UsernameMissingException;
import com.gfa.greenbay.exceptions.login.UserNotFoundException;
import com.gfa.greenbay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginRequestValidatorImpl implements LoginRequestValidator {

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;

  @Autowired
  public LoginRequestValidatorImpl(
      AuthenticationManager authenticationManager,
      UserRepository userRepository) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
  }

  public void validateLoginRequestDTO(LoginRequestDTO request) {
    validateLoginRequestNotNull(request);
    validateLoginRequestHasUsername(request);
    validateLoginRequestHasPassword(request);
    validateUsernameExists(request);
  }

  //region private validator methods methods for validateLoginRequestDTO()
  private void validateLoginRequestNotNull(LoginRequestDTO request) {
    if (request == null) {
      throw new LoginRequestNullException();
    }
  }

  private void validateLoginRequestHasUsername(LoginRequestDTO request) {
    if (request.getUsername() == null || request.getUsername().equals("")) {
      throw new UsernameMissingException();
    }
  }

  private void validateLoginRequestHasPassword(LoginRequestDTO request) {
    if (request.getPassword() == null || request.getPassword().equals("")) {
      throw new PasswordMissingException();
    }
  }

  private void validateUsernameExists(LoginRequestDTO request) {
    if (userRepository.findUserByUsername(request.getUsername()).isEmpty()) {
      throw new UserNotFoundException();
    }
  }

  //  private void validatePasswordIsCorrect(LoginRequestDTO request) {
  //    String storedPwd = userRepository.findUserByUsername(request.getUsername()).get().getPassword();
  //    if (!storedPwd.equals(request.getPassword())) {
  //      throw new IncorrectPasswordException();
  //    }
  //}
  //endregion private validator methods for validateLoginRequestDTO()

  public void authenticateUserOrThrowException(LoginRequestDTO request) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    } catch (BadCredentialsException e) {
      throw new IncorrectUserNameOrPassword();
    }
  }
}

