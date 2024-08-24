package com.example.customershopbackend.entities.staff.feture.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record StaffResponse(
        String uuid,
        String image,
        String name,
        String gender,
        LocalDate birthDate,
        String phone,
        String email,
        String address,
        String position,
        LocalDate hiredDate,
        BigDecimal salary
        ) {
}
