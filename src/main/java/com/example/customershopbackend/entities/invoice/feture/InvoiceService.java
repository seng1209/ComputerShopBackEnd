package com.example.customershopbackend.entities.invoice.feture;

import com.example.customershopbackend.entities.invoice.feture.dto.InvoiceRequest;
import com.example.customershopbackend.entities.invoice.feture.dto.InvoiceResponse;
import com.example.customershopbackend.entities.invoice.feture.dto.UpdateInvoiceRequest;

import java.util.List;

public interface InvoiceService {

    InvoiceResponse createInvoice(InvoiceRequest invoiceRequest);

    InvoiceResponse updateByUuid(String uuid, UpdateInvoiceRequest updateInvoiceRequest);

    void updateIsDeletedByUuid(String uuid);

    InvoiceResponse findByUuid(String uuid);

    List<InvoiceResponse> findAll();

}
