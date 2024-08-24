package com.example.customershopbackend.entities.sales_products.feture;

import com.example.customershopbackend.entities.sales_products.SalesProducts;
import com.example.customershopbackend.entities.sales_products.feture.dto.SalesProductsRequest;
import com.example.customershopbackend.entities.sales_products.feture.dto.SalesProductsResponse;
import com.example.customershopbackend.entities.sales_products.feture.dto.UpdateSalesProductsRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SalesProductsMapper {

    SalesProducts fromSalesProductsRequest(SalesProductsRequest salesProductsRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateSalesProductsRequest(@MappingTarget SalesProducts salesProducts, UpdateSalesProductsRequest updateSalesProductsRequest);

    @Mapping(source = "sale", target = "sale")
    @Mapping(source = "product", target = "product")
    SalesProductsResponse toSalesProductsResponse(SalesProducts salesProducts);

    List<SalesProductsResponse> toSalesProductsResponseList(List<SalesProducts> salesProductsList);

}
