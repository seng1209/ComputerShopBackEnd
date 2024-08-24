package com.example.customershopbackend.entities.payment.feture.dto;

import com.example.customershopbackend.entities.customer.feture.dto.CustomerResponse;
import com.example.customershopbackend.entities.staff.feture.dto.StaffResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponse(
        String uuid,
        LocalDateTime payDate,
        CustomerResponse customer,
        StaffResponse staff,
        BigDecimal paidAmount,
        Boolean isDeleted
) {
}
