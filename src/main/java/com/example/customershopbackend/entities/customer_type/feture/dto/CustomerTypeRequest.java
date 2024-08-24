package com.example.customershopbackend.entities.customer_type.feture.dto;

import jakarta.validation.constraints.NotBlank;

public record CustomerTypeRequest(
        @NotBlank(message = "Type is required.")
        String type) {
}
