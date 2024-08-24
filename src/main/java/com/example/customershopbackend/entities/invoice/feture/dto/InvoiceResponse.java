package com.example.customershopbackend.entities.invoice.feture.dto;

import com.example.customershopbackend.entities.customer.feture.dto.CustomerResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record InvoiceResponse(
        String uuid,
        LocalDateTime invoiceDate,
        BigDecimal totalAmount,
        BigDecimal paidAmount,
        BigDecimal oweAmount,
        CustomerResponse customer,
        Boolean isDeleted
) {
}
