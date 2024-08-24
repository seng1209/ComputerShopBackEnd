package com.example.customershopbackend.entities.category.feture.dto;

import lombok.Builder;

@Builder
public record CategoryResponse(
        String uuid,
        String name) {
}
