package com.example.customershopbackend.entities.payment.feture.dto;

import java.math.BigDecimal;

public record UpdatePaymentRequest(
        String customerPhone,
        String staffUuid,
        BigDecimal paidAmount
) {
}
