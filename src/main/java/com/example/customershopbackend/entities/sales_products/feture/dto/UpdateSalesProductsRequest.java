package com.example.customershopbackend.entities.sales_products.feture.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record UpdateSalesProductsRequest(
        String saleUuid,
        String productUuid,
        @Positive(message = "Sale Quantity must be Positive number and large than 0.")
        Integer saleQuantity,
        @Positive(message = "Sale Unit Price must be Positive number and large than 0.")
        BigDecimal saleUnitPrice,
        @PositiveOrZero(message = "Discount must be Positive number.")
        BigDecimal discount
) {
}
