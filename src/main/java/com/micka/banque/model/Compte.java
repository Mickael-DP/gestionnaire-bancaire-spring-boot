package com.micka.banque.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compte {

    private Long id;
    private String numeroCompte;
    private double solde;
    private String titulaire;
    private String type;
}
