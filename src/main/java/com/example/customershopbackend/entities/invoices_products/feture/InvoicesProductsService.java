package com.example.customershopbackend.entities.invoices_products.feture;

import com.example.customershopbackend.entities.invoices_products.feture.dto.InvoicesProductsRequest;
import com.example.customershopbackend.entities.invoices_products.feture.dto.InvoicesProductsResponse;
import com.example.customershopbackend.entities.invoices_products.feture.dto.UpdateInvoicesProducts;

import java.util.List;

public interface InvoicesProductsService {

    InvoicesProductsResponse createInvoicesProducts(InvoicesProductsRequest invoicesProductsRequest);

    InvoicesProductsResponse updateByUuid(String uuid, UpdateInvoicesProducts updateInvoicesProducts);

    InvoicesProductsResponse findByUuid(String uuid);

    List<InvoicesProductsResponse> findAll();

    List<InvoicesProductsResponse> findAllByInvoiceUuid(String invoiceUuid);

}
