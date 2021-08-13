package com.gfa.greenbay.services;

import com.gfa.greenbay.entitiesanddtos.LoginRequestDTO;
import com.gfa.greenbay.entitiesanddtos.LoginResponseDTO;

public interface UserService {

  LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
