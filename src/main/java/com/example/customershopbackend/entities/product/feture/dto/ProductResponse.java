package com.example.customershopbackend.entities.product.feture.dto;

import com.example.customershopbackend.entities.category.feture.dto.CategoryResponse;

import java.math.BigDecimal;

public record ProductResponse(
        String uuid,
        String image,
        String name,
        String description,
        Integer stockQuantity,
        BigDecimal unitPrice,
        BigDecimal saleUnitPrice,
        BigDecimal discount,
        Boolean isDeleted,
        CategoryResponse category
) {
}
