package com.example.customershopbackend.entities.product.feture.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record UpdateProductRequest(
        String image,
        String name,
        String description,
        @PositiveOrZero(message = "Stock Quantity must be Positive number or 0.")
        Integer stockQuantity,
        @PositiveOrZero(message = "Unit Price must be Positive number or 0.")
        BigDecimal unitPrice,
        @PositiveOrZero(message = "Sale Unit Price must be Positive number or 0.")
        BigDecimal saleUnitPrice,
        @PositiveOrZero(message = "Discount must be Positive number or 0.")
        BigDecimal discount,
        String categoryUuid
) {
}
