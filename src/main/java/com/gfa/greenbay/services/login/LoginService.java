package com.gfa.greenbay.services.login;

import com.gfa.greenbay.entitiesanddtos.LoginRequestDTO;
import com.gfa.greenbay.entitiesanddtos.LoginResponseDTO;

public interface LoginService {

  LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO);
}
