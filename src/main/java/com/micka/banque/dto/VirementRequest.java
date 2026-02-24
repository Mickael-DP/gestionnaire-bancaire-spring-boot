package com.micka.banque.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class VirementRequest {
    @NotNull
    private Long idSource;

    @NotNull
    private Long idDestination;

    @Positive
    private double montant;
}
