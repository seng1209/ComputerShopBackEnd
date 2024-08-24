package com.example.customershopbackend.entities.invoice.feture;

import com.example.customershopbackend.entities.invoice.feture.dto.InvoiceRequest;
import com.example.customershopbackend.entities.invoice.feture.dto.InvoiceResponse;
import com.example.customershopbackend.entities.invoice.feture.dto.UpdateInvoiceRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public InvoiceResponse createInvoice(@Valid @RequestBody InvoiceRequest invoiceRequest){
        return invoiceService.createInvoice(invoiceRequest);
    }

    @PatchMapping("/{uuid}")
    public InvoiceResponse updateByUuid(@PathVariable String uuid, @Valid @RequestBody UpdateInvoiceRequest updateInvoiceRequest){
        return invoiceService.updateByUuid(uuid, updateInvoiceRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{uuid}")
    public void updateIsDeletedByUuid(@PathVariable String uuid){
        invoiceService.updateIsDeletedByUuid(uuid);
    }

    @GetMapping("/{uuid}")
    public InvoiceResponse findByUuid(@PathVariable String uuid){
        return invoiceService.findByUuid(uuid);
    }

    @GetMapping
    public List<InvoiceResponse> findAll(){
        return invoiceService.findAll();
    }

}
