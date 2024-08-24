package com.example.customershopbackend.entities.imports_products.fetrue.dto;

import com.example.customershopbackend.entities.imports.feture.dto.ImportResponse;
import com.example.customershopbackend.entities.product.feture.dto.ProductResponse;

import java.math.BigDecimal;

public record ImportsProductsResponse(
        String uuid,
        ImportResponse imports,
        ProductResponse product,
        Integer importQuantity,
        BigDecimal unitPrice,
        BigDecimal amount
) {
}
