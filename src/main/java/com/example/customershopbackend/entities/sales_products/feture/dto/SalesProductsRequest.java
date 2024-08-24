package com.example.customershopbackend.entities.sales_products.feture.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record SalesProductsRequest(
        @NotBlank(message = "Sale UUID is required.")
        String saleUuid,
        @NotBlank(message = "Product UUID is required.")
        String productUuid,
        @NotNull(message = "Sale Quantity is required.")
        @Positive(message = "Sale Quantity must be Positive number and large than 0.")
        Integer saleQuantity,
        @NotNull(message = "Sale Unit Price is required.")
        @Positive(message = "Sale Unit Price must be Positive number and large than 0.")
        BigDecimal saleUnitPrice,
        @NotNull(message = "Discount is required.")
        @PositiveOrZero(message = "Discount must be Positive number.")
        BigDecimal discount
) {
}
