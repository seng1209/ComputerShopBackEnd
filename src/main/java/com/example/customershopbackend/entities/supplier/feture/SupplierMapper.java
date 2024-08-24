package com.example.customershopbackend.entities.supplier.feture;

import com.example.customershopbackend.entities.supplier.Supplier;
import com.example.customershopbackend.entities.supplier.feture.dto.SupplierRequest;
import com.example.customershopbackend.entities.supplier.feture.dto.SupplierResponse;
import com.example.customershopbackend.entities.supplier.feture.dto.UpdateSupplierRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    Supplier fromSupplierRequest(SupplierRequest supplierRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateSupplierRequest(@MappingTarget Supplier supplier, UpdateSupplierRequest updateSupplierRequest);

    SupplierResponse toSupplierResponse(Supplier supplier);

    List<SupplierResponse> toSupplierResponseList(List<Supplier> suppliers);

}
