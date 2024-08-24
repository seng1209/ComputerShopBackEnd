package com.example.customershopbackend.entities.invoices_products.feture;

import com.example.customershopbackend.entities.invoices_products.feture.dto.InvoicesProductsRequest;
import com.example.customershopbackend.entities.invoices_products.feture.dto.InvoicesProductsResponse;
import com.example.customershopbackend.entities.invoices_products.feture.dto.UpdateInvoicesProducts;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invoices_products")
@RequiredArgsConstructor
public class InvoicesProductsController {

    private final InvoicesProductsService invoicesProductsService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public InvoicesProductsResponse createInvoicesProducts(@Valid @RequestBody InvoicesProductsRequest invoicesProductsRequest){
        return invoicesProductsService.createInvoicesProducts(invoicesProductsRequest);
    }

    @PatchMapping("/{uuid}")
    public InvoicesProductsResponse updateByUuid(@PathVariable String uuid, @Valid @RequestBody UpdateInvoicesProducts updateInvoicesProducts){
        return invoicesProductsService.updateByUuid(uuid, updateInvoicesProducts);
    }

    @GetMapping("/{uuid}")
    public InvoicesProductsResponse findByUuid(@PathVariable String uuid){
        return invoicesProductsService.findByUuid(uuid);
    }

    @GetMapping
    public List<InvoicesProductsResponse> findAll(){
        return invoicesProductsService.findAll();
    }

    @GetMapping("/invoice/{uuid}")
    public List<InvoicesProductsResponse> findAllByInvoiceUuid(@PathVariable String uuid){
        return invoicesProductsService.findAllByInvoiceUuid(uuid);
    }

}
