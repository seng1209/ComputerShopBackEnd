package com.example.customershopbackend.entities.customer.feture.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateCustomerType(
        @NotBlank(message = "Type is required.")
        String type
) {
}
