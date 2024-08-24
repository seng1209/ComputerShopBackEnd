package com.example.customershopbackend.entities.sales_products.feture;

import com.example.customershopbackend.entities.sales_products.feture.dto.SalesProductsRequest;
import com.example.customershopbackend.entities.sales_products.feture.dto.SalesProductsResponse;
import com.example.customershopbackend.entities.sales_products.feture.dto.UpdateSalesProductsRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sales_products")
@RequiredArgsConstructor
public class SalesProductsController {

    private final SalesProductsService salesProductsService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SalesProductsResponse createSalesProducts(@Valid @RequestBody SalesProductsRequest salesProductsRequest){
        return salesProductsService.createSalesProducts(salesProductsRequest);
    }

    @PatchMapping("/{uuid}")
    public SalesProductsResponse updateByUuid(@PathVariable String uuid, @Valid @RequestBody UpdateSalesProductsRequest updateSalesProductsRequest){
        return salesProductsService.updateByUuid(uuid, updateSalesProductsRequest);
    }

    @GetMapping("/{uuid}")
    public SalesProductsResponse findByUuid(@PathVariable String uuid){
        return salesProductsService.findByUuid(uuid);
    }

    @GetMapping
    public List<SalesProductsResponse> findAll(){
        return salesProductsService.findAll();
    }

    @GetMapping("/sale/{uuid}")
    public List<SalesProductsResponse> findAllBySaleUuid(@PathVariable String uuid){
        return salesProductsService.findAllBySaleUuid(uuid);
    }

}
