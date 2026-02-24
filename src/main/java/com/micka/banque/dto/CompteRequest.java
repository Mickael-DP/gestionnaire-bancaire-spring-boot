package com.micka.banque.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompteRequest {
    @NotBlank
    private String titulaire;

    @NotBlank
    private String type;
}
