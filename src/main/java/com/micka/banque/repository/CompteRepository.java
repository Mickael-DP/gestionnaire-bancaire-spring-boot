package com.micka.banque.repository;

import com.micka.banque.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {

    List<Compte> findByOwner_Username(String username);
    List<Compte> findByOwner_Email(String email);

    Optional<Compte> findByIdAndOwner_Username(Long id, String username);
    Optional<Compte> findByIdAndOwner_Email(Long id, String email);
}