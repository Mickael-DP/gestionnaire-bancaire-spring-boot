package com.micka.banque.service;

import com.micka.banque.exception.AccessDeniedException;
import com.micka.banque.exception.CompteNotFoundException;
import com.micka.banque.exception.SoldeInsuffisantException;
import com.micka.banque.model.Compte;
import com.micka.banque.model.User;
import com.micka.banque.repository.CompteRepository;
import com.micka.banque.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompteService {

    private final CompteRepository compteRepository;
    private final UserRepository userRepository;

    private Compte getCompteWithOwnerCheck(Long id, String email) {
        return compteRepository.findByIdAndOwner_Email(id, email)
                .orElseThrow(() -> {
                    if (compteRepository.existsById(id)) {
                        throw new AccessDeniedException("Vous n'avez pas accès à ce compte");
                    } else {
                        throw new CompteNotFoundException(id);
                    }
                });
    }

    public Compte creerCompte (String username, String titulaire, String type ){

        User user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        String numeroCompte = "FR" + System.currentTimeMillis();

        Compte compte = new Compte();
        compte.setCompteNumber(numeroCompte);
        compte.setTitulaire(titulaire);
        compte.setType(type);
        compte.setSolde(0.0);
        compte.setOwner(user);

        return compteRepository.save(compte);
    }

    public void suppCompte (String username, Long id){
        getCompteWithOwnerCheck(id, username);
        compteRepository.deleteById(id);

    }

    public List<Compte> listerComptesUtilisateur(String email) {
        return compteRepository.findByOwner_Email(email);
    }

    public Compte consulterCompte(String username, Long id) {
        return getCompteWithOwnerCheck(id, username);
    }

    public Compte deposer(String username, Long id, double montant) {
        Compte compte = getCompteWithOwnerCheck(id, username);
        compte.setSolde(compte.getSolde() + montant);
        return compteRepository.save(compte);
    }

    public  Compte retirer (String username, Long id, double montant){
        Compte compte = getCompteWithOwnerCheck(id, username);

        if (compte.getSolde() < montant){
       throw new SoldeInsuffisantException();
        }

        compte.setSolde(compte.getSolde() - montant);

        return compteRepository.save(compte);

    }

    @Transactional
    public Compte virement (String username, Long idSource, Long idDestination, double montant){
        Compte compteSource = getCompteWithOwnerCheck(idSource, username);
        Compte compteDestination = getCompteWithOwnerCheck(idDestination, username);

        if (compteSource.getSolde() < montant){
            throw new SoldeInsuffisantException();
        }

        compteDestination.setSolde(compteDestination.getSolde() + montant);
        compteSource.setSolde(compteSource.getSolde() - montant );

        compteRepository.save(compteSource);

        return compteRepository.save(compteDestination);
    }

}
