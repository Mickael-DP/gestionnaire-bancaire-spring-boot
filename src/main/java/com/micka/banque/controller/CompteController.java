package com.micka.banque.controller;

import com.micka.banque.dto.CompteRequest;
import com.micka.banque.dto.OperationRequest;
import com.micka.banque.dto.VirementRequest;
import com.micka.banque.model.Compte;
import com.micka.banque.service.CompteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
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
    public List<Compte> listerComptes(){
        return compteService.listerComptes();
    }

    @PostMapping
    public Compte creerCompte(@RequestBody @Valid CompteRequest request) {
        return compteService.creerCompte(request.getTitulaire(), request.getType());
    }

    @DeleteMapping("/{id}")
    public void suppCompte (@PathVariable Long id){
         compteService.suppCompte(id);
    }

    @GetMapping("/{id}")
    public Compte consulterCompte(@PathVariable Long id){
        return compteService.consulterCompte(id);
    }

    @PutMapping("/{id}/depot")
    public Compte deposer(@PathVariable Long id, @RequestBody @Valid OperationRequest request){
        return compteService.deposer(id, request.getMontant());
    }

    @PutMapping("/{id}/retrait")
    public Compte retirer (@PathVariable Long id, @RequestBody @Valid OperationRequest request){
        return compteService.retirer(id, request.getMontant());
    }

    @PostMapping("/virement")
    public Compte virement (@RequestBody @Valid VirementRequest request){
        return  compteService.virement(request.getIdSource(), request.getIdDestination(), request.getMontant());
    }
}