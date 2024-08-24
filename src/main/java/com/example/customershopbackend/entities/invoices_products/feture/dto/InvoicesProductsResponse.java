package com.example.customershopbackend.entities.invoices_products.feture.dto;

import com.example.customershopbackend.entities.invoice.feture.dto.InvoiceResponse;
import com.example.customershopbackend.entities.product.feture.dto.ProductResponse;

import java.math.BigDecimal;

public record InvoicesProductsResponse(
        String uuid,
        InvoiceResponse invoice,
        ProductResponse product,
        Integer saleQuantity,
        BigDecimal saleUnitPrice,
        BigDecimal amount
) {
}
