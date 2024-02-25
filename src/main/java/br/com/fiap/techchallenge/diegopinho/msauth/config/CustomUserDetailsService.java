package br.com.fiap.techchallenge.diegopinho.msauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.fiap.techchallenge.diegopinho.msauth.entities.UserCredential;
import br.com.fiap.techchallenge.diegopinho.msauth.repositories.UserCredentialRepository;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserCredentialRepository repository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<UserCredential> credential = repository.findByEmail(email); // username = email
    return credential.map(CustomUserDetails::new)
        .orElseThrow(() -> new UsernameNotFoundException("user not found with email :" + email));
  }
}
