package com.example.customershopbackend.entities.imports_products.fetrue;

import com.example.customershopbackend.entities.imports_products.fetrue.dto.ImportsProductsRequest;
import com.example.customershopbackend.entities.imports_products.fetrue.dto.ImportsProductsResponse;
import com.example.customershopbackend.entities.imports_products.fetrue.dto.UpdateImportsProductsRequest;

import java.util.List;

public interface ImportsProductsService {

    ImportsProductsResponse createImportsProducts(ImportsProductsRequest importsProductsRequest);

    ImportsProductsResponse updateByUuid(String uuid, UpdateImportsProductsRequest updateImportsProductsRequest);

    ImportsProductsResponse findByUuid(String uuid);

    List<ImportsProductsResponse> findAll();

    List<ImportsProductsResponse> findAllByImportUuid(String importUuid);

    void updateTotalAmount(String importUuid);

}
