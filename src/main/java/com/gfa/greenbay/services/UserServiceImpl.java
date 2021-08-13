package com.gfa.greenbay.services;

import com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs.LoginRequestDTO;
import com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs.LoginResponseDTO;
import com.gfa.greenbay.entitiesanddtos.User;
import com.gfa.greenbay.exceptions.login.UserNotFoundException;
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

  public User findUserByUserName(String username) {
    return userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
  }
}
