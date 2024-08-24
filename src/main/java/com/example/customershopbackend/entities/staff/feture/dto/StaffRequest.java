package com.example.customershopbackend.entities.staff.feture.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record StaffRequest(
        @NotBlank(message = "Image is required.")
        String image,
        @NotBlank(message = "Name is required.")
        String name,
        @NotBlank(message = "Gender is required.")
        String gender,
//        @NotBlank(message = "Birth Date is required.")
        @NotNull(message = "Birth Date is required.")
        LocalDate birthDate,
        @NotBlank(message = "Phone is required.")
        String phone,
        @NotBlank(message = "Email is required.")
        String email,
        @NotBlank(message = "Address is required.")
        String address,
        @NotBlank(message = "Position is required.")
        String position,
        @NotNull(message = "Salary is required.")
        @Positive(message = "Salary must be Positive number and large than 0.")
        BigDecimal salary
) {
}
