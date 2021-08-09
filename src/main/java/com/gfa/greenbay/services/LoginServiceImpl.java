package com.gfa.greenbay.services;

import com.gfa.greenbay.entitiesanddtos.LoginRequestDTO;
import com.gfa.greenbay.entitiesanddtos.LoginResponseDTO;
import com.gfa.greenbay.entitiesanddtos.User;
import com.gfa.greenbay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

  private final UserRepository userRepository;
  private final LoginRequestValidator loginRequestValidator;

  @Autowired
  public LoginServiceImpl(UserRepository userRepository,
      LoginRequestValidator loginRequestValidator) {
    this.userRepository = userRepository;
    this.loginRequestValidator = loginRequestValidator;
  }

  @Override
  public LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO) {
    loginRequestValidator.validateLoginRequestDTO(loginRequestDTO);
    User currentUser = userRepository.findUserByUsername(loginRequestDTO.getUsername()).get();
    return new LoginResponseDTO("vlmi", currentUser.getMoney());
  }
}
