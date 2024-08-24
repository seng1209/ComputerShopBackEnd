package com.example.customershopbackend.entities.supplier.feture.dto;

import jakarta.validation.constraints.NotBlank;

public record SupplierRequest(
        @NotBlank(message = "Name is required.")
        String name,
        @NotBlank(message = "Phone is required.")
        String phone,
        String address
) {
}
