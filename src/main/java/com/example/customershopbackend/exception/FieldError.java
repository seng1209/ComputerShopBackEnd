package com.example.customershopbackend.exception;

import lombok.Builder;

@Builder
public record FieldError(
        String field,
        String message
) {
}
