package com.micka.banque.service;

import com.micka.banque.exception.CompteNotFoundException;
import com.micka.banque.exception.SoldeInsuffisantException;
import com.micka.banque.model.Compte;
import com.micka.banque.model.User;
import com.micka.banque.repository.CompteRepository;
import com.micka.banque.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompteServiceTest {

    @Mock
    private CompteRepository compteRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CompteService compteService;

    private User testUser;
    private Compte testCompte;

    @BeforeEach
    void setUp() {
        // Créer un utilisateur de test
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");
        testUser.setEmail("test@test.com");

        // Créer un compte de test
        testCompte = new Compte();
        testCompte.setId(1L);
        testCompte.setCompteNumber("FR123456");
        testCompte.setSolde(100.0);
        testCompte.setTitulaire("Test User");
        testCompte.setType("Courant");
        testCompte.setOwner(testUser);
    }

    @Test
    void retirer_soldeInsuffisant_exception() {
        // Given
        testCompte.setSolde(50.0);
        when(compteRepository.findByIdAndOwner_Username(1L, "testuser"))
                .thenReturn(Optional.of(testCompte));

        // When & Then
        assertThrows(SoldeInsuffisantException.class, () -> {
            compteService.retirer("testuser", 1L, 100.0);
        });
    }

    @Test
    void retirer_compteInexistant_doitLancerException() {
        // Given
        when(compteRepository.findByIdAndOwner_Username(99L, "testuser"))
                .thenReturn(Optional.empty());
        when(compteRepository.existsById(99L)).thenReturn(false);

        // When & Then
        assertThrows(CompteNotFoundException.class, () -> {
            compteService.retirer("testuser", 99L, 100.0);
        });
    }

    @Test
    void retirer_retraitValide_doitMettreAJourLeSolde() {
        // Given
        testCompte.setSolde(50.0);
        when(compteRepository.findByIdAndOwner_Username(1L, "testuser"))
                .thenReturn(Optional.of(testCompte));
        when(compteRepository.save(testCompte)).thenReturn(testCompte);

        // When
        Compte resultat = compteService.retirer("testuser", 1L, 50.0);

        // Then
        assertEquals(0.0, resultat.getSolde());
    }
}