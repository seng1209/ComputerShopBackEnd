package com.example.customershopbackend.entities.product.feture;

import com.example.customershopbackend.entities.product.feture.dto.ProductRequest;
import com.example.customershopbackend.entities.product.feture.dto.ProductResponse;
import com.example.customershopbackend.entities.product.feture.dto.UpdateProductRequest;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse updateByUuid(String uuid, UpdateProductRequest updateProductRequest);

    void updateIsDeletedByUuid(String uuid);

    ProductResponse findByUuid(String uuid);

    List<ProductResponse> findAll();

}
