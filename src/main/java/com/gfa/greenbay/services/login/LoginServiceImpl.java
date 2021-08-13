package com.gfa.greenbay.services.login;

import com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs.LoginRequestDTO;
import com.gfa.greenbay.entitiesanddtos.dtosandvalueobjs.LoginResponseDTO;
import com.gfa.greenbay.entitiesanddtos.User;
import com.gfa.greenbay.repositories.UserRepository;
import com.gfa.greenbay.utils.security.JwtUtilServiceImpl;
import com.gfa.greenbay.utils.security.UserDetailsImpl;
import com.gfa.greenbay.utils.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

  private final LoginRequestValidator loginRequestValidator;
  private final UserDetailsServiceImpl userDetailsService;
  private final JwtUtilServiceImpl jwtUtilService;
  private final UserRepository userRepository;

  @Autowired
  public LoginServiceImpl(
      UserDetailsServiceImpl userDetailsService,
      JwtUtilServiceImpl jwtUtilService,
      UserRepository userRepository,
      LoginRequestValidator loginRequestValidator) {
    this.userDetailsService = userDetailsService;
    this.jwtUtilService = jwtUtilService;
    this.userRepository = userRepository;
    this.loginRequestValidator = loginRequestValidator;
  }

  @Override
  public LoginResponseDTO loginUser(LoginRequestDTO request) {
    loginRequestValidator.validateLoginRequestDTO(request);
    loginRequestValidator.authenticateUserOrThrowException(request);
    UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService
        .loadUserByUsername(request.getUsername());
    User requestUser = userRepository.findUserByUsername(request.getUsername()).get();
    return new LoginResponseDTO(jwtUtilService.generateToken(userDetails), requestUser.getMoney());
  }
}
