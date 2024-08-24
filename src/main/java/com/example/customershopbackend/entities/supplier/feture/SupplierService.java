package com.example.customershopbackend.entities.supplier.feture;

import com.example.customershopbackend.entities.supplier.feture.dto.SupplierRequest;
import com.example.customershopbackend.entities.supplier.feture.dto.SupplierResponse;
import com.example.customershopbackend.entities.supplier.feture.dto.UpdateSupplierRequest;

import java.util.List;

public interface SupplierService {

    SupplierResponse createSupplier(SupplierRequest supplierRequest);

    SupplierResponse updateByUuid(String uuid, UpdateSupplierRequest updateSupplierRequest);

    void updateIsDeletedByUuid(String uuid);

    SupplierResponse findByUuid(String uuid);

    List<SupplierResponse> findAll();

}
