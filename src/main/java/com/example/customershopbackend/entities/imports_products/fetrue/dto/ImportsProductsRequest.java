package com.example.customershopbackend.entities.imports_products.fetrue.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ImportsProductsRequest(
        @NotBlank(message = "Import UUID is required.")
        String importsUuid,
        @NotBlank(message = "Product Uuid is required.")
        String productUuid,
        @NotNull(message = "Import Quantity is required.")
        @Positive(message = "Import Quantity must be Positive number and large than 0.")
        Integer importQuantity,
        @NotNull(message = "Unit Price is required.")
        @Positive(message = "Unit Price must be Positive number and large than 0.")
        BigDecimal unitPrice
) {
}
