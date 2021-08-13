package com.gfa.greenbay.controllers;

import com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs.LoginRequestDTO;
import com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs.LoginResponseDTO;
import com.gfa.greenbay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/greenbay/api")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(value = "/login")
  public ResponseEntity<?> login(@RequestBody(required = false) LoginRequestDTO request) {
    LoginResponseDTO response = userService.login(request);
    return ResponseEntity.ok().body(response);
  }
}