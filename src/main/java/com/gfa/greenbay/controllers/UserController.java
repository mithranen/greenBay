package com.gfa.greenbay.controllers;

import com.gfa.greenbay.entitiesanddtos.LoginRequestDTO;
import com.gfa.greenbay.entitiesanddtos.LoginResponseDTO;
import com.gfa.greenbay.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/greenbay/api/")
public class UserController {

  private final LoginService loginService;

  @Autowired
  public UserController(LoginService loginService) {
    this.loginService = loginService;
  }

  @PostMapping("login")
  public ResponseEntity<?> login(@RequestBody(required = false) LoginRequestDTO request) {
    LoginResponseDTO response = loginService.loginUser(request);
    return ResponseEntity.ok().body(response);
  }
}
