package com.example.customershopbackend.entities.product.feture;

import com.example.customershopbackend.entities.product.Product;
import com.example.customershopbackend.entities.product.feture.dto.ProductRequest;
import com.example.customershopbackend.entities.product.feture.dto.ProductResponse;
import com.example.customershopbackend.entities.product.feture.dto.UpdateProductRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product fromProductRequest(ProductRequest productRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateProductRequest(@MappingTarget Product product, UpdateProductRequest productRequest);

    @Mapping(source = "category", target = "category")
    ProductResponse toProductResponse(Product product);

    List<ProductResponse> toProductResponseList(List<Product> products);

}
