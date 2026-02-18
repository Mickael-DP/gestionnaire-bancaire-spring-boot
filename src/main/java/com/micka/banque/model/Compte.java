package com.micka.banque.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comptes")
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_compte", unique = true)
    private String compteNumber;

    @Column(name = "solde")
    private double solde;

    @Column(name = "titulaire")
    private String titulaire;

    @Column(name = "type")
    private String type;
}