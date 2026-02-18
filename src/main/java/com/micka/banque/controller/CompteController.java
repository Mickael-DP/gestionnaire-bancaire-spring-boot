package com.micka.banque.controller;

import com.micka.banque.model.Compte;
import com.micka.banque.service.CompteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comptes")
@RequiredArgsConstructor
public class CompteController {

    private final CompteService compteService;

    @GetMapping
    public List<Compte> listerComptes(){
        return compteService.listerComptes();
    }

    @PostMapping
    public Compte creerCompte(@RequestParam String titulaire,
                              @RequestParam String type) {
        return compteService.creerCompte(titulaire, type);
    }

    @GetMapping("/{id}")
    public Compte consulterCompte(@PathVariable Long id){
        return compteService.consulterCompte(id);
    }

    @PutMapping("/{id}/depot")
    public Compte deposer(@PathVariable Long id, @RequestParam double montant){
        return compteService.deposer(id, montant);
    }

    @PutMapping("/{id}/retrait")
    public Compte retirer (@PathVariable Long id, @RequestParam double montant){
        return compteService.retirer(id, montant);
    }
}