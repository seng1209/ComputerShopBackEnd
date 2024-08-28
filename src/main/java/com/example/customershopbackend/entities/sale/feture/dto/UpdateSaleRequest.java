package com.example.customershopbackend.entities.sale.feture.dto;

import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record UpdateSaleRequest(
        String customerPhone,
        String staffUuid
) {
}
