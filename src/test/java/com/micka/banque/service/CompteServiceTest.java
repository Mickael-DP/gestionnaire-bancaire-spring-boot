package com.micka.banque.service;

import com.micka.banque.exception.CompteNotFoundException;
import com.micka.banque.exception.SoldeInsuffisantException;
import com.micka.banque.model.Compte;
import com.micka.banque.repository.CompteRepository;
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

    @InjectMocks
    private CompteService compteService;

    @Test
    void retirer_soldeInsuffisant_exception() {
        Compte compte = new Compte();
        compte.setId(1L);
        compte.setSolde(50.0);

        when(compteRepository.findById(1L)).thenReturn(Optional.of(compte));

        assertThrows(SoldeInsuffisantException.class, () -> {
            compteService.retirer(1L, 100.0);
        });
    }

    @Test
    void retirer_compteInexistant_doitLancerException() {
        when(compteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(CompteNotFoundException.class, () -> {
            compteService.retirer(99L, 100.0);
        });
    }

    @Test
    void retirer_retraitValide_doitMettreAJourLeSolde() {
        Compte compte = new Compte();
        compte.setId(1L);
        compte.setSolde(50.0);

        when(compteRepository.findById(1L)).thenReturn(Optional.of(compte));
        when(compteRepository.save(compte)).thenReturn(compte);

        Compte resultat = compteService.retirer(1L, 50.0);

        assertEquals(0.0, resultat.getSolde());
    }
}
