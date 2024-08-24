package com.example.customershopbackend.entities.invoice.feture.dto;

import java.math.BigDecimal;

public record UpdateInvoiceRequest (
        BigDecimal oweAmount,
        String customerPhone
){
}
