package com.example.customershopbackend.entities.imports_products.fetrue;

import com.example.customershopbackend.entities.imports_products.fetrue.dto.ImportsProductsRequest;
import com.example.customershopbackend.entities.imports_products.fetrue.dto.ImportsProductsResponse;
import com.example.customershopbackend.entities.imports_products.fetrue.dto.UpdateImportsProductsRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/imports_products")
@RequiredArgsConstructor
public class ImportsProductsController {

    private final ImportsProductsService importsProductsService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ImportsProductsResponse createImportsProducts(@Valid @RequestBody ImportsProductsRequest importsProductsRequest){
        return importsProductsService.createImportsProducts(importsProductsRequest);
    }

    @PatchMapping("/{uuid}")
    public ImportsProductsResponse updateByUuid(@PathVariable String uuid, @Valid @RequestBody UpdateImportsProductsRequest updateImportsProductsRequest){
        return importsProductsService.updateByUuid(uuid, updateImportsProductsRequest);
    }

    @GetMapping("/{uuid}")
    public ImportsProductsResponse findByUuid(@PathVariable String uuid){
        return importsProductsService.findByUuid(uuid);
    }

    @GetMapping
    public List<ImportsProductsResponse> findAll(){
        return importsProductsService.findAll();
    }

    @GetMapping("/import/{uuid}")
    public List<ImportsProductsResponse> findAllByImportUuid(@PathVariable String uuid){
        return importsProductsService.findAllByImportUuid(uuid);
    }

    @PutMapping("/imports/{importUuid}")
    public void updateTotalAmount(@PathVariable String importUuid){
        importsProductsService.updateTotalAmount(importUuid);
    }

}
