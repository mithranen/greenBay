package com.gfa.greenbay.controllers;

import com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs.ErrorDTO;
import com.gfa.greenbay.exceptions.login.IncorrectUserNameOrPassword;
import com.gfa.greenbay.exceptions.login.LoginRequestNullException;
import com.gfa.greenbay.exceptions.login.PasswordMissingException;
import com.gfa.greenbay.exceptions.login.UsernameMissingException;
import com.gfa.greenbay.exceptions.login.UserNotFoundException;
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

  @ExceptionHandler(value = {UsernameMissingException.class})
  public ResponseEntity<?> handleIfUsernameMissing() {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorDTO("You did not give username!"));
  }

  @ExceptionHandler(value = {PasswordMissingException.class})
  public ResponseEntity<?> handleIfPasswordMissing() {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorDTO("You did not give password!"));
  }

  @ExceptionHandler(value = UserNotFoundException.class)
  public ResponseEntity<?> handleUsernameNotFound() {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ErrorDTO("User not found with given username!"));
  }

  @ExceptionHandler(IncorrectUserNameOrPassword.class)
  public ResponseEntity<?> handleAuthenticationProblem() {
    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(new ErrorDTO("Incorrect username or password!"));
  }
  
  //  @ExceptionHandler(value = IncorrectPasswordException.class)
  //  public ResponseEntity<?> handleIncorrectPwd() {
  //    return ResponseEntity
  //        .status(HttpStatus.UNAUTHORIZED)
  //        .body(new ErrorDTO("Incorrect password!"));
  //  }
}
