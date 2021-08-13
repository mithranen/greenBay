package com.gfa.greenbay.services;

import com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs.LoginRequestDTO;
import com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs.LoginResponseDTO;
import com.gfa.greenbay.entitiesanddtos.User;

public interface UserService {

  LoginResponseDTO login(LoginRequestDTO loginRequestDTO);

  User findUserByUserName(String username);
}
