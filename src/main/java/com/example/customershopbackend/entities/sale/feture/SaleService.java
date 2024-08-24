package com.example.customershopbackend.entities.sale.feture;

import com.example.customershopbackend.entities.sale.feture.dto.SaleRequest;
import com.example.customershopbackend.entities.sale.feture.dto.SaleResponse;
import com.example.customershopbackend.entities.sale.feture.dto.UpdateSaleRequest;

import java.util.List;

public interface SaleService {

    SaleResponse createSale(SaleRequest saleRequest);

    SaleResponse updateByUuid(String uuid, UpdateSaleRequest updateSaleRequest);

    void updateIsDeletedByUuid(String uuid);

    SaleResponse findByUuid(String uuid);

    List<SaleResponse> findAll();

}
