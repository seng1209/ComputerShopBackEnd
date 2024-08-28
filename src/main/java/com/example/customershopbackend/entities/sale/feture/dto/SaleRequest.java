package com.example.customershopbackend.entities.sale.feture.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record SaleRequest(
        @NotBlank(message = "Customer Phone is required.")
        String customerPhone,
        @NotBlank(message = "Staff UUID is required.")
        String staffUuid
//        @PositiveOrZero(message = "Total Amount must be Positive number.")
//        BigDecimal totalAmount
) {
}
