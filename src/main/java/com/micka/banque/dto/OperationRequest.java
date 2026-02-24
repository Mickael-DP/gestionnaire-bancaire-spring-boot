package com.micka.banque.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OperationRequest {

    @Positive
    private double montant;
}
