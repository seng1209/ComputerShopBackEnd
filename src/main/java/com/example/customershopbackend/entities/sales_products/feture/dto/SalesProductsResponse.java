package com.example.customershopbackend.entities.sales_products.feture.dto;

import com.example.customershopbackend.entities.product.feture.dto.ProductResponse;
import com.example.customershopbackend.entities.sale.feture.dto.SaleResponse;

import java.math.BigDecimal;

public record SalesProductsResponse(
        String uuid,
        SaleResponse sale,
        ProductResponse product,
        Integer saleQuantity,
        BigDecimal saleUnitPrice,
        BigDecimal discount,
        BigDecimal amount
) {
}
