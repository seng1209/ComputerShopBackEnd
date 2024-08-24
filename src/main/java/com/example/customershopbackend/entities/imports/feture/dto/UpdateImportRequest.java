package com.example.customershopbackend.entities.imports.feture.dto;

import java.math.BigDecimal;

public record UpdateImportRequest(
        String supplierUuid,
        String staffUuid,
        BigDecimal totalAmount
) {
}
