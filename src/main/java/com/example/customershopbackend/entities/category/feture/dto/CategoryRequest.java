package com.example.customershopbackend.entities.category.feture.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank(message = "Name is required.")
        String name) {
}
