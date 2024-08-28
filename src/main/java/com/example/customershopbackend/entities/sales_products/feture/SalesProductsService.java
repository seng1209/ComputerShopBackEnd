package com.example.customershopbackend.entities.sales_products.feture;

import com.example.customershopbackend.entities.sales_products.feture.dto.SalesProductsRequest;
import com.example.customershopbackend.entities.sales_products.feture.dto.SalesProductsResponse;
import com.example.customershopbackend.entities.sales_products.feture.dto.UpdateSalesProductsRequest;

import java.util.List;

public interface SalesProductsService {

    SalesProductsResponse createSalesProducts(SalesProductsRequest salesProductsRequest);

    SalesProductsResponse updateByUuid(String uuid, UpdateSalesProductsRequest updateSalesProductsRequest);

    SalesProductsResponse findByUuid(String uuid);

    List<SalesProductsResponse> findAll();

    List<SalesProductsResponse> findAllBySaleUuid(String saleUuid);

    void updateTotalAmount(String saleUuid);

}
