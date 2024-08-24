package com.example.customershopbackend.entities.customer.feture.dto;

import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(
        String image,
        @NotBlank(message = "Name is required.")
        String name,
        @NotBlank(message = "Phone is required.")
        String phone,
        @NotBlank(message = "Email is required.")
        String email,
        String address
        ) {
}
