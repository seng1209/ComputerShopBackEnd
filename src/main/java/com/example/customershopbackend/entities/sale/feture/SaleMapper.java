package com.example.customershopbackend.entities.sale.feture;

import com.example.customershopbackend.entities.sale.Sale;
import com.example.customershopbackend.entities.sale.feture.dto.SaleRequest;
import com.example.customershopbackend.entities.sale.feture.dto.SaleResponse;
import com.example.customershopbackend.entities.sale.feture.dto.UpdateSaleRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleMapper {

    Sale fromSaleRequest(SaleRequest saleRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateSaleRequest(@MappingTarget Sale sale, UpdateSaleRequest updateSaleRequest);

    @Mapping(source = "staff", target = "staff")
    @Mapping(source = "customer", target = "customer")
    SaleResponse toSaleResponse(Sale sale);

    List<SaleResponse> toSaleResponseList(List<Sale> sales);

}
