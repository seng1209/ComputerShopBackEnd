package com.example.customershopbackend.entities.product.feture;

import com.example.customershopbackend.entities.product.feture.dto.ProductRequest;
import com.example.customershopbackend.entities.product.feture.dto.ProductResponse;
import com.example.customershopbackend.entities.product.feture.dto.UpdateProductRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductResponse createProduct(@Valid @RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }

    @PatchMapping("/{uuid}")
    public ProductResponse updateByUuid(@PathVariable String uuid, @Valid @RequestBody UpdateProductRequest updateProductRequest){
        return productService.updateByUuid(uuid, updateProductRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{uuid}")
    public void updateIsDeletedByUuid(@PathVariable String uuid){
        productService.updateIsDeletedByUuid(uuid);
    }

    @GetMapping("/{uuid}")
    public ProductResponse findByUuid(@PathVariable String uuid){
        return productService.findByUuid(uuid);
    }

    @GetMapping
    public List<ProductResponse> findAll(){
        return productService.findAll();
    }

}
