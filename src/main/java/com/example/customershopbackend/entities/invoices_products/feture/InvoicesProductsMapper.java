package com.example.customershopbackend.entities.invoices_products.feture;

import com.example.customershopbackend.entities.invoices_products.InvoicesProducts;
import com.example.customershopbackend.entities.invoices_products.feture.dto.InvoicesProductsRequest;
import com.example.customershopbackend.entities.invoices_products.feture.dto.InvoicesProductsResponse;
import com.example.customershopbackend.entities.invoices_products.feture.dto.UpdateInvoicesProducts;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoicesProductsMapper {

    InvoicesProducts fromInvoiceProductsRequest(InvoicesProductsRequest invoicesProductsRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateInvoicesProductsRequest(@MappingTarget InvoicesProducts invoicesProducts, UpdateInvoicesProducts updateInvoicesProducts);

    @Mapping(source = "invoice", target = "invoice")
    @Mapping(source = "product", target = "product")
    InvoicesProductsResponse toInvoicesProductsResponse(InvoicesProducts invoicesProducts);

    List<InvoicesProductsResponse> toInvoicesProductsResponseList(List<InvoicesProducts> invoicesProductsList);

}
