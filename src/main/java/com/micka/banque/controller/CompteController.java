package com.micka.banque.controller;

import com.micka.banque.model.Compte;
import com.micka.banque.service.CompteService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comptes")
@RequiredArgsConstructor
@Validated
public class CompteController {

    private final CompteService compteService;

    @GetMapping
    public List<Compte> listerComptes(){
        return compteService.listerComptes();
    }

    @PostMapping
    public Compte creerCompte(@RequestParam @NotBlank String titulaire,
                              @RequestParam @NotBlank String type) {
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

    @PostMapping("/virement")
    public Compte virement (@RequestParam Long idSource, @RequestParam Long idDestination, @RequestParam double montant){
        return  compteService.virement(idSource, idDestination, montant);
    }
}