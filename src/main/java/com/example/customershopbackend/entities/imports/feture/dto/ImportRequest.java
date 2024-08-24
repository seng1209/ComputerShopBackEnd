package com.example.customershopbackend.entities.imports.feture.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ImportRequest(
        @NotBlank(message = "Supplier UUID is required.")
        String supplierUuid,
        @NotBlank(message = "Staff UUID is required.")
        String staffUuid,
        @NotNull(message = "Total Amount is required.")
        @Positive(message = "Total Amount must be Positive number and large than 0!")
        BigDecimal totalAmount
) {
}
