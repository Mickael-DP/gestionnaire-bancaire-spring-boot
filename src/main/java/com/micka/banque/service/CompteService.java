package com.micka.banque.service;

import com.micka.banque.model.Compte;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompteService {

    private List<Compte> comptes = new ArrayList<>();
    private Long compteurId = 1L;

    public Compte creerCompte (String titulaire, String type ){

        String numeroCompte = "FR" + compteurId + "00000";

        Compte compte = new Compte(
                compteurId,
                numeroCompte,
                0.0,
                titulaire,
                type
        );

        comptes.add(compte);

        compteurId++;

        return compte;
    }

    public List<Compte> listerComptes() {
        return comptes;
    }

    public  Compte consulterCompte(Long id ){
        return comptes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Compte deposer (Long id, double montant){
        Compte compte = consulterCompte(id);

        if (compte == null) {
            return null;
        }

        compte.setSolde(compte.getSolde() + montant);

        return compte;

    }

    public  Compte retirer (Long id, double montant){
        Compte compte = consulterCompte(id);

        if (compte == null) {
            return null;
        }

        if (compte.getSolde() < montant){
            return null;
        }

        compte.setSolde(compte.getSolde() - montant);

        return compte;

    }


}
