package com.example.customershopbackend.entities.imports_products.fetrue;

import com.example.customershopbackend.entities.imports_products.ImportsProducts;
import com.example.customershopbackend.entities.imports_products.fetrue.dto.ImportsProductsRequest;
import com.example.customershopbackend.entities.imports_products.fetrue.dto.ImportsProductsResponse;
import com.example.customershopbackend.entities.imports_products.fetrue.dto.UpdateImportsProductsRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImportsProductsMapper {

    ImportsProducts fromImportsProductsRequest(ImportsProductsRequest importsProductsRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateImportsProductsRequest(@MappingTarget ImportsProducts importsProducts, UpdateImportsProductsRequest updateImportsProductsRequest);

    @Mapping(source = "imports", target = "imports")
    @Mapping(source = "product", target = "product")
    ImportsProductsResponse toImportsProductsResponse(ImportsProducts importsProducts);

    List<ImportsProductsResponse> toImportsProductsResponseList(List<ImportsProducts> importsProductsList);

}
