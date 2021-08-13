package com.gfa.greenbay.utils.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EncoderConfigurator {

  @Bean
  public PasswordEncoder passwordEncoder() {
    //TODO no registration no hash pwd yet
    //return new BCryptPasswordEncoder(BCryptVersion.$2Y, 10, null);
    return NoOpPasswordEncoder.getInstance();

  }
}
