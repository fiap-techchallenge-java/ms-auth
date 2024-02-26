package br.com.fiap.techchallenge.diegopinho.msauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fiap.techchallenge.diegopinho.msauth.entities.UserCredential;
import br.com.fiap.techchallenge.diegopinho.msauth.repositories.UserCredentialRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AuthService {

  @Autowired
  private UserCredentialRepository repository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtService jwtService;

  public String saveUser(UserCredential credential) {
    String encodedPassword = passwordEncoder.encode(credential.getPassword());
    credential.setPassword(encodedPassword);
    repository.save(credential);
    return "user added to the system";
  }

  public String generateToken(String username) {
    return jwtService.generateToken(username);
  }

  public String validateToken(String token) {
    return jwtService.validateToken(token);
  }

  public UserCredential getUserCredentialByEmail(String email) {
    return this.repository
        .findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("User Credential Not Found!"));
  }

}
