package com.gfa.greenbay.services;

import com.gfa.greenbay.entitiesanddtos.LoginRequestDTO;

public interface LoginRequestValidator {

  void validateLoginRequestDTO(LoginRequestDTO requestDTO);
}
