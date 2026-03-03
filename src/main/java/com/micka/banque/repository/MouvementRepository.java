package com.micka.banque.repository;

import com.micka.banque.model.Mouvement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MouvementRepository extends JpaRepository<Mouvement, Long> {
    List<Mouvement> findByCompte_IdOrderByDateDesc(Long compteId);

    List<Mouvement> id(Long id);
}
