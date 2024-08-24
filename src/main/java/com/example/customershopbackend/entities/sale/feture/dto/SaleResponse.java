package com.example.customershopbackend.entities.sale.feture.dto;

import com.example.customershopbackend.entities.customer.feture.dto.CustomerResponse;
import com.example.customershopbackend.entities.staff.Staff;
import com.example.customershopbackend.entities.staff.feture.dto.StaffResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SaleResponse(
        String uuid,
        LocalDateTime saleDate,
        CustomerResponse customer,
        StaffResponse staff,
        BigDecimal totalAmount,
        Boolean isDeleted
) {
}
