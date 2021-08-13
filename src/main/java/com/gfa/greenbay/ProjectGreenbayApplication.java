package com.gfa.greenbay;

import com.gfa.greenbay.entitiesanddtos.User;
import com.gfa.greenbay.repositories.UserRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectGreenbayApplication implements CommandLineRunner {

  private final UserRepository userRepository;

  @Autowired
  public ProjectGreenbayApplication(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(ProjectGreenbayApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    User user = new User(1L, "kiscica", "password123", 230, "USER", new ArrayList<>());
    userRepository.save(user);
  }
}
