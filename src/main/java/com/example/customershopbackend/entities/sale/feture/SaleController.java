package com.example.customershopbackend.entities.sale.feture;

import com.example.customershopbackend.entities.sale.feture.dto.SaleRequest;
import com.example.customershopbackend.entities.sale.feture.dto.SaleResponse;
import com.example.customershopbackend.entities.sale.feture.dto.UpdateSaleRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SaleResponse createSale(@Valid @RequestBody SaleRequest saleRequest){
        return saleService.createSale(saleRequest);
    }

    @PatchMapping("/{uuid}")
    public SaleResponse updateByUuid(@PathVariable String uuid, @Valid @RequestBody UpdateSaleRequest updateSaleRequest){
        return saleService.updateByUuid(uuid, updateSaleRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{uuid}")
    public void updateIsDeletedByUuid(@PathVariable String uuid){
        saleService.updateIsDeletedByUuid(uuid);
    }

    @GetMapping("/{uuid}")
    public SaleResponse findByUuid(@PathVariable String uuid){
        return saleService.findByUuid(uuid);
    }

    @GetMapping
    public List<SaleResponse> findAll(){
        return saleService.findAll();
    }

}
