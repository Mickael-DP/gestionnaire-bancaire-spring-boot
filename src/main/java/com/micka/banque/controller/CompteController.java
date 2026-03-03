package com.micka.banque.controller;

import com.micka.banque.dto.CompteRequest;
import com.micka.banque.dto.OperationRequest;
import com.micka.banque.dto.VirementRequest;
import com.micka.banque.model.Compte;
import com.micka.banque.model.Mouvement;
import com.micka.banque.service.CompteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/comptes")
@RequiredArgsConstructor
@Validated
public class CompteController {

    private final CompteService compteService;

    @GetMapping
    public List<Compte> listerComptes(Authentication authentication){
        String username = authentication.getName();
        return compteService.listerComptesUtilisateur(username);
    }

    @PostMapping
    public Compte creerCompte(@RequestBody @Valid CompteRequest request, Authentication authentication) {
        String username = authentication.getName();
        return compteService.creerCompte(username, request.getTitulaire(), request.getType());
    }

    @DeleteMapping("/{id}")
    public void suppCompte (@PathVariable Long id, Authentication authentication){
        String username = authentication.getName();
        compteService.suppCompte(username,id);
    }

    @GetMapping("/{id}")
    public Compte consulterCompte(@PathVariable Long id, Authentication authentication){
        String username = authentication.getName();
        return compteService.consulterCompte(username, id);
    }
    @GetMapping("/{id}/historiques")
    public List<Mouvement> historiqueMouvement(@PathVariable Long id, Authentication authentication){
        String username = authentication.getName();
        return compteService.historiqueDeMouvement(username, id);
    }

    @PutMapping("/{id}/depot")
    public Compte deposer(@PathVariable Long id, @RequestBody @Valid OperationRequest request, Authentication authentication){
        String username = authentication.getName();
        return compteService.deposer(username, id, request.getMontant());
    }

    @PutMapping("/{id}/retrait")
    public Compte retirer (@PathVariable Long id, @RequestBody @Valid OperationRequest request, Authentication authentication){
        String username = authentication.getName();
        return compteService.retirer(username, id, request.getMontant());
    }

    @PostMapping("/virement")
    public Compte virement (@RequestBody @Valid VirementRequest request, Authentication authentication){
        String username = authentication.getName();
        return  compteService.virement(username,request.getIdSource(), request.getIdDestination(), request.getMontant());
    }
}