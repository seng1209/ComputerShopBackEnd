package com.example.customershopbackend.entities.payment.feture.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record PaymentRequest(
        @NotBlank(message = "Customer Phone is required.")
        String customerPhone,
        @NotBlank(message = "Staff UUID is required.")
        String staffUuid,
        @PositiveOrZero(message = "Paid Amount must be Positive number.")
        BigDecimal paidAmount
        ) {
}
