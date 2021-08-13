package com.gfa.greenbay.services;

import com.gfa.greenbay.entitiesanddtos.LoginRequestDTO;
import com.gfa.greenbay.entitiesanddtos.LoginResponseDTO;
import com.gfa.greenbay.repositories.UserRepository;
import com.gfa.greenbay.services.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final LoginService loginService;

  @Autowired
  public UserServiceImpl(UserRepository userRepository,
      LoginService loginService) {
    this.userRepository = userRepository;
    this.loginService = loginService;
  }

  @Override
  public LoginResponseDTO login(LoginRequestDTO request) {
    return loginService.loginUser(request);
  }
}
