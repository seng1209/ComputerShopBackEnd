package com.example.customershopbackend.entities.invoice.feture.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record InvoiceRequest(
        @NotNull(message = "Owe Amount is required.")
        @PositiveOrZero(message = "Owe Amount must be Positive number.")
        BigDecimal oweAmount,
        @NotBlank(message = "Customer Phone is required.")
        String customerPhone
) {
}
