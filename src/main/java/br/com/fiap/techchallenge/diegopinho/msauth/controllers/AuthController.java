package br.com.fiap.techchallenge.diegopinho.msauth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.techchallenge.diegopinho.msauth.dtos.AuthRequest;
import br.com.fiap.techchallenge.diegopinho.msauth.entities.UserCredential;
import br.com.fiap.techchallenge.diegopinho.msauth.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthService service;

  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping("/register")
  public String addNewUser(@RequestBody UserCredential user) {
    return service.saveUser(user);
  }

  @PostMapping("/token")
  public String getToken(@RequestBody AuthRequest authRequest) {
    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
        authRequest.getPassword());
    Authentication authenticate = authenticationManager.authenticate(auth);
    if (authenticate.isAuthenticated()) {
      return service.generateToken(authRequest.getEmail());
    } else {
      throw new RuntimeException("invalid access");
    }
  }

  @GetMapping("/validate")
  public String validateToken(@RequestParam("token") String token) {
    service.validateToken(token);
    return "Token is valid";
  }
}
