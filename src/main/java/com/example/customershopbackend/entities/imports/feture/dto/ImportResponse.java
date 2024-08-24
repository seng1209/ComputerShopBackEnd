package com.example.customershopbackend.entities.imports.feture.dto;

import com.example.customershopbackend.entities.staff.feture.dto.StaffResponse;
import com.example.customershopbackend.entities.supplier.feture.dto.SupplierResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ImportResponse(
        String uuid,
        LocalDateTime importDate,
        SupplierResponse supplier,
        StaffResponse staff,
        BigDecimal totalAmount,
        Boolean isDeleted
) {
}
