package com.example.customershopbackend.entities.customer.feture.dto;

import com.example.customershopbackend.entities.customer_type.feture.dto.CustomerTypeResponse;
import lombok.Builder;

@Builder
public record CustomerResponse(
        String uuid,
        String image,
        String name,
        String phone,
        String email,
        String address,
        CustomerTypeResponse customerType
) {
}
