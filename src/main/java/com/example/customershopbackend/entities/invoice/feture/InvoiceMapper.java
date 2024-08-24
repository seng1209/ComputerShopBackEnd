package com.example.customershopbackend.entities.invoice.feture;

import com.example.customershopbackend.entities.invoice.Invoice;
import com.example.customershopbackend.entities.invoice.feture.dto.InvoiceRequest;
import com.example.customershopbackend.entities.invoice.feture.dto.InvoiceResponse;
import com.example.customershopbackend.entities.invoice.feture.dto.UpdateInvoiceRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    Invoice fromInvoiceRequest(InvoiceRequest invoiceRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateInvoiceRequest(@MappingTarget Invoice invoice, UpdateInvoiceRequest updateInvoiceRequest);

    @Mapping(source = "customer", target = "customer")
    InvoiceResponse toInvoiceResponse(Invoice invoice);

    List<InvoiceResponse> toInvoiceResponseList(List<Invoice> invoices);

}
