package com.example.customershopbackend.entities.customer.feture.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateCustomerRequest(
        String image,
        String name,
        String phone,
        String email,
        String address
) {
}
