package com.example.customershopbackend.entities.supplier.feture;

import com.example.customershopbackend.entities.supplier.Supplier;
import com.example.customershopbackend.entities.supplier.feture.dto.SupplierRequest;
import com.example.customershopbackend.entities.supplier.feture.dto.SupplierResponse;
import com.example.customershopbackend.entities.supplier.feture.dto.UpdateSupplierRequest;
import com.example.customershopbackend.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService{

    private final SupplierMapper supplierMapper;
    private final SupplierRepository supplierRepository;

    @Override
    public SupplierResponse createSupplier(SupplierRequest supplierRequest) {
        Supplier supplier = supplierMapper.fromSupplierRequest(supplierRequest);
        supplier.setUuid(RandomUtil.random6Digits());
        supplier.setIsDeleted(false);
        supplierRepository.save(supplier);
        return supplierMapper.toSupplierResponse(supplier);
    }

    @Override
    public SupplierResponse updateByUuid(String uuid, UpdateSupplierRequest updateSupplierRequest) {

        if (supplierRepository.existsByUuid(uuid)){
            Supplier supplier = supplierRepository.findByUuid(uuid).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found!")
            );
            supplierMapper.fromUpdateSupplierRequest(supplier, updateSupplierRequest);
            supplierRepository.save(supplier);
            return supplierMapper.toSupplierResponse(supplier);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found!");
    }

    @Transactional
    @Override
    public void updateIsDeletedByUuid(String uuid) {
        supplierRepository.updateByIsDeleted(uuid);
    }

    @Override
    public SupplierResponse findByUuid(String uuid) {
        Supplier supplier = supplierRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found!")
        );
        return supplierMapper.toSupplierResponse(supplier);
    }

    @Override
    public List<SupplierResponse> findAll() {
        List<Supplier> suppliers = supplierRepository.findAllByIsDeletedIsFalse();
        return supplierMapper.toSupplierResponseList(suppliers);
    }
}
