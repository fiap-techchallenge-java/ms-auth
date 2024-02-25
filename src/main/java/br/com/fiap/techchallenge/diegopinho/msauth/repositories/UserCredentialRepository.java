package br.com.fiap.techchallenge.diegopinho.msauth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.techchallenge.diegopinho.msauth.entities.UserCredential;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer> {

    Optional<UserCredential> findByName(String username);

    Optional<UserCredential> findByEmail(String email);

}
