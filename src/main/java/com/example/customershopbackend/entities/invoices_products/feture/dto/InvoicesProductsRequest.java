package com.example.customershopbackend.entities.invoices_products.feture.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record InvoicesProductsRequest(
        @NotBlank(message = "Invoice UUID is required.")
        String invoiceUuid,
        @NotBlank(message = "Product UUID is required.")
        String productUuid,
        @NotNull(message = "Sale Quantity is required.")
        @Positive(message = "Sale Quantity must be Positive number and large than 0.")
        Integer saleQuantity,
        @NotNull(message = "Sale Unit Price is required.")
        @Positive(message = "Sale Unit Price must be Positive number and large than 0.")
        BigDecimal saleUnitPrice
) {
}
