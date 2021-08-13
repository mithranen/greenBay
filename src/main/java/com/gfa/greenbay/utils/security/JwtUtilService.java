package com.gfa.greenbay.utils.security;

import java.util.List;
import org.springframework.security.core.GrantedAuthority;

public interface JwtUtilService {

  String generateToken(UserDetailsImpl userDetails);

  boolean validateToken(String authToken);

  String getUsernameFromToken(String token);

  Long getUserIdFromToken(String token);

  //region methods for service unit tests
  void setSecret(String secret);

  void setJwtExpirationInMs(int jwtExpirationInMs);

  List<GrantedAuthority> getAuthoritiesFromToken(String token);
  //endregion
}
