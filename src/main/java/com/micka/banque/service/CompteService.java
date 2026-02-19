package com.micka.banque.service;

import com.micka.banque.exception.CompteNotFoundException;
import com.micka.banque.exception.SoldeInsuffisantException;
import com.micka.banque.model.Compte;
import com.micka.banque.repository.CompteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompteService {

    private final CompteRepository compteRepository;

    public Compte creerCompte (String titulaire, String type ){

        String numeroCompte = "FR" + System.currentTimeMillis();

        Compte compte = new Compte();
        compte.setCompteNumber(numeroCompte);
        compte.setTitulaire(titulaire);
        compte.setType(type);
        compte.setSolde(0.0);

        return compteRepository.save(compte);
    }

    public List<Compte> listerComptes() {
        return compteRepository.findAll();
    }

    public  Compte consulterCompte(Long id ){
        return compteRepository.findById(id).orElseThrow(() -> new CompteNotFoundException(id));
    }

    public Compte deposer (Long id, double montant){
        Compte compte = consulterCompte(id);

        compte.setSolde(compte.getSolde() + montant);

        return compteRepository.save(compte);

    }

    public  Compte retirer (Long id, double montant){
        Compte compte = consulterCompte(id);

        if (compte.getSolde() < montant){
       throw new SoldeInsuffisantException();
        }

        compte.setSolde(compte.getSolde() - montant);

        return compteRepository.save(compte);

    }

}
