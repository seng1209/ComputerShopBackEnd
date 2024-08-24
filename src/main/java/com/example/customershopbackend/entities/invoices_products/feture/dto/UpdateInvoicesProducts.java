package com.example.customershopbackend.entities.invoices_products.feture.dto;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record UpdateInvoicesProducts(
        String invoiceUuid,
        String productUuid,
        @Positive(message = "Sale Quantity must be Positive number and large than 0.")
        Integer saleQuantity,
        @Positive(message = "Sale Unit Price must be Positive number and large than 0.")
        BigDecimal saleUnitPrice
) {
}
