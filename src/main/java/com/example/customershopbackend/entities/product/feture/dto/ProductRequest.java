package com.example.customershopbackend.entities.product.feture.dto;

import com.example.customershopbackend.entities.category.Category;
import com.example.customershopbackend.entities.category.feture.dto.CategoryResponse;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "Image is required.")
        String image,
        @NotBlank(message = "Name is required.")
        String name,
        String description,
        @NotNull(message = "Stock Quantity is required.")
        @PositiveOrZero(message = "Stock Quantity must be Positive number or 0.")
        Integer stockQuantity,
        @NotNull(message = "Unit Price is required.")
        @PositiveOrZero(message = "Unit Price must be Positive number or 0.")
        BigDecimal unitPrice,
        @NotNull(message = "Sale Unit Price is required.")
        @PositiveOrZero(message = "Sale Unit Price must be Positive number or 0.")
        BigDecimal saleUnitPrice,
        @NotNull(message = "Discount is required.")
        @PositiveOrZero(message = "Discount must be Positive number or 0.")
        BigDecimal discount,
        @NotBlank(message = "Category UUID is required.")
        String categoryUuid
) {
}
