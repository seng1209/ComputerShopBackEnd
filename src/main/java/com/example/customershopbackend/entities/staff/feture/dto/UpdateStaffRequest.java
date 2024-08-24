package com.example.customershopbackend.entities.staff.feture.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateStaffRequest(
        String image,
        String name,
        String gender,
        LocalDate birthDate,
        String phone,
        String email,
        String address,
        String position,
        @Positive(message = "Salary must be Positive number and large than 0.")
        BigDecimal salary
) {
}
