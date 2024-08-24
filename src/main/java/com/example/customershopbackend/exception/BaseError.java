package com.example.customershopbackend.exception;

import lombok.Builder;

import java.time.LocalTime;

@Builder
public record BaseError<T>(
        LocalTime time,
        String message,
        String code,
        Boolean state,
        T error
) {
}
