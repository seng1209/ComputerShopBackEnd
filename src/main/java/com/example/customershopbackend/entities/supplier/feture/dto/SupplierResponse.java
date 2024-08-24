package com.example.customershopbackend.entities.supplier.feture.dto;

public record SupplierResponse(
        String uuid,
        String name,
        String phone,
        String address,
        Boolean isDeleted
) {
}
