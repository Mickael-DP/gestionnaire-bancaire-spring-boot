package com.micka.banque.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompteRequest {

    @NotBlank
    private String nomCompte;

    @NotBlank
    private String type;
}
