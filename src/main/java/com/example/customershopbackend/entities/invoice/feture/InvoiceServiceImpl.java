package com.example.customershopbackend.entities.invoice.feture;

import com.example.customershopbackend.entities.customer.Customer;
import com.example.customershopbackend.entities.customer.feture.CustomerRepository;
import com.example.customershopbackend.entities.invoice.Invoice;
import com.example.customershopbackend.entities.invoice.feture.dto.InvoiceRequest;
import com.example.customershopbackend.entities.invoice.feture.dto.InvoiceResponse;
import com.example.customershopbackend.entities.invoice.feture.dto.UpdateInvoiceRequest;
import com.example.customershopbackend.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService{

    private final InvoiceMapper invoiceMapper;
    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;

    @Override
    public InvoiceResponse createInvoice(InvoiceRequest invoiceRequest) {

        Customer customer = customerRepository.findByPhone(invoiceRequest.customerPhone()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found!")
        );

        Invoice invoice = invoiceMapper.fromInvoiceRequest(invoiceRequest);
        invoice.setUuid(RandomUtil.random6Digits());
        invoice.setInvoiceDate(LocalDateTime.now());
        invoice.setCustomer(customer);
        invoice.setIsDeleted(false);

        invoiceRepository.save(invoice);
        return invoiceMapper.toInvoiceResponse(invoice);
    }

    @Override
    public InvoiceResponse updateByUuid(String uuid, UpdateInvoiceRequest updateInvoiceRequest) {

        if (invoiceRepository.existsByUuid(uuid)){
            Invoice invoice = invoiceRepository.findByUuid(uuid).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found!")
            );
            invoiceMapper.fromUpdateInvoiceRequest(invoice, updateInvoiceRequest);
            if (updateInvoiceRequest.customerPhone() != null){
                Customer customer = customerRepository.findByPhone(updateInvoiceRequest.customerPhone()).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found!")
                );
                invoice.setCustomer(customer);
            }
            invoiceRepository.save(invoice);
            return invoiceMapper.toInvoiceResponse(invoice);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found!");
    }

    @Transactional
    @Override
    public void updateIsDeletedByUuid(String uuid) {
        invoiceRepository.updateByIsDeleted(uuid);
    }

    @Override
    public InvoiceResponse findByUuid(String uuid) {
        Invoice invoice = invoiceRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found!")
        );
        return invoiceMapper.toInvoiceResponse(invoice);
    }

    @Override
    public List<InvoiceResponse> findAll() {
        List<Invoice> invoices = invoiceRepository.findAllByIsDeletedIsFalse();
        return invoiceMapper.toInvoiceResponseList(invoices);
    }
}
