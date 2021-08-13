package com.gfa.greenbay.utils.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class JwtUtilServiceImpl implements JwtUtilService {

  private String secret;
  private int jwtExpirationInMs;

  //TODO cant do withh just UserDetails - userId problem
  @Override
  public String generateToken(UserDetailsImpl userDetails) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("roles", getRolesAsString(userDetails));
    return buildToken(claims, userDetails);
  }

  private String getRolesAsString(UserDetailsImpl userDetails) {
    Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
    return authorities.stream().map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));
  }

  private String buildToken(Map<String, Object> claims, UserDetailsImpl userDetails) {
    return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
        .setId(userDetails.getUserId().toString())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
        .signWith(SignatureAlgorithm.HS512, secret).compact();
  }

  public boolean validateToken(String authToken) {
    try {
      Jws<Claims> claims = getParsedJwt(authToken);
      return true;
    } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
      throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
    } catch (ExpiredJwtException ex) {
      throw ex;
    }
  }

  private Jws<Claims> getParsedJwt(String authToken) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
  }

  public String getUsernameFromToken(String token) {
    Claims claims = getParsedJwt(token).getBody();
    return claims.getSubject();
  }

  public Long getUserIdFromToken(String token) {
    Claims claims = getParsedJwt(token).getBody();
    return Long.valueOf(claims.getId());
  }

  //region methods for service unit tests
  @Override
  @Value("${jwt.secret}")
  public void setSecret(String secret) {
    this.secret = secret;
  }

  @Override
  @Value("${jwt.expirationDateInMs}")
  public void setJwtExpirationInMs(int jwtExpirationInMs) {
    this.jwtExpirationInMs = jwtExpirationInMs;
  }

  public List<GrantedAuthority> getAuthoritiesFromToken(String token) {
    String roles = getParsedJwt(token).getBody().get("roles", String.class);
    List<GrantedAuthority> authorities = parseAuthoritiesToList(roles);

    if (authorities.size() == 0) {
      return null;
    }
    return authorities;
  }

  private List<GrantedAuthority> parseAuthoritiesToList(String roles) {
    if (roles == null) {
      return null;
    }
    return Arrays.stream(roles.split(","))
        .filter(Objects::nonNull)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  } //endregion
}
