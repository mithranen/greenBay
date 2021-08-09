package com.gfa.greenbay.controllers;

import com.gfa.greenbay.entitiesanddtos.ErrorDTO;
import com.gfa.greenbay.exceptions.IncorrectPasswordException;
import com.gfa.greenbay.exceptions.LoginRequestNullException;
import com.gfa.greenbay.exceptions.UsernameNotFoundException;
import com.gfa.greenbay.exceptions.UsernameOrPasswordIsEmptyException;
import com.gfa.greenbay.exceptions.UsernameOrPasswordMissingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LoginExceptionHandler {

  @ExceptionHandler(value = LoginRequestNullException.class)
  public ResponseEntity<?> handleNullRequest() {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorDTO("You did not give username and password!"));
  }

  @ExceptionHandler(value = {UsernameOrPasswordMissingException.class,
      UsernameOrPasswordIsEmptyException.class})
  public ResponseEntity<?> handleIfUsernameOrPwdMissing() {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorDTO("You did not give username or password!"));
  }

  @ExceptionHandler(value = UsernameNotFoundException.class)
  public ResponseEntity<?> handleUsernameNotFound() {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ErrorDTO("Username not found!"));
  }

  @ExceptionHandler(value = IncorrectPasswordException.class)
  public ResponseEntity<?> handleIncorrectPwd() {
    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(new ErrorDTO("Username not found!"));
  }
}
