package com.gfa.greenbay.services.login;

import com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs.LoginRequestDTO;
import com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs.LoginResponseDTO;

public interface LoginService {

  LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO);
}
