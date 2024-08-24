package com.example.customershopbackend.entities.supplier.feture;

import com.example.customershopbackend.entities.supplier.feture.dto.SupplierRequest;
import com.example.customershopbackend.entities.supplier.feture.dto.SupplierResponse;
import com.example.customershopbackend.entities.supplier.feture.dto.UpdateSupplierRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SupplierResponse createSupplier(@Valid @RequestBody SupplierRequest supplierRequest){
        return supplierService.createSupplier(supplierRequest);
    }

    @PatchMapping("/{uuid}")
    public SupplierResponse updateByUuid(@PathVariable String uuid, @Valid @RequestBody UpdateSupplierRequest updateSupplierRequest){
        return supplierService.updateByUuid(uuid, updateSupplierRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{uuid}")
    public void updateIsDeletedByUuid(@PathVariable String uuid){
        supplierService.updateIsDeletedByUuid(uuid);
    }

    @GetMapping("/{uuid}")
    public SupplierResponse findByUuid(@PathVariable String uuid){
        return supplierService.findByUuid(uuid);
    }

    @GetMapping
    public List<SupplierResponse> findAll(){
        return supplierService.findAll();
    }

}
