package com.gfa.greenbay.services.login;

import com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs.LoginRequestDTO;

public interface LoginRequestValidator {

  void validateLoginRequestDTO(LoginRequestDTO request);

  void authenticateUserOrThrowException(LoginRequestDTO request);
}
