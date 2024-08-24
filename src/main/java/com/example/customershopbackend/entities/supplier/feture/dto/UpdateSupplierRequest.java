package com.example.customershopbackend.entities.supplier.feture.dto;

public record UpdateSupplierRequest(
        String name,
        String phone,
        String address
) {
}
