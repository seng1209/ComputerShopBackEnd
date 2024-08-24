package com.example.customershopbackend.entities.imports_products.fetrue.dto;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record UpdateImportsProductsRequest(
        String importsUuid,
        String productUuid,
        @Positive(message = "Import Quantity must be Positive number and large than 0.")
        Integer importQuantity,
        @Positive(message = "Unit Price must be Positive number and large than 0.")
        BigDecimal unitPrice
) {
}
