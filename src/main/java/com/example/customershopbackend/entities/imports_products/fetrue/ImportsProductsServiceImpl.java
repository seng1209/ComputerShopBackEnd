package com.example.customershopbackend.entities.imports_products.fetrue;

import com.example.customershopbackend.entities.imports.Import;
import com.example.customershopbackend.entities.imports.feture.ImportRepository;
import com.example.customershopbackend.entities.imports_products.ImportsProducts;
import com.example.customershopbackend.entities.imports_products.fetrue.dto.ImportsProductsRequest;
import com.example.customershopbackend.entities.imports_products.fetrue.dto.ImportsProductsResponse;
import com.example.customershopbackend.entities.imports_products.fetrue.dto.UpdateImportsProductsRequest;
import com.example.customershopbackend.entities.product.Product;
import com.example.customershopbackend.entities.product.feture.ProductRepository;
import com.example.customershopbackend.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportsProductsServiceImpl implements ImportsProductsService{

    private final ImportsProductsRepository importsProductsRepository;
    private final ImportsProductsMapper importsProductsMapper;
    private final ImportRepository importRepository;
    private final ProductRepository productRepository;

    @Override
    public ImportsProductsResponse createImportsProducts(ImportsProductsRequest importsProductsRequest) {

        Import imports = importRepository.findByUuid(importsProductsRequest.importsUuid()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Import not found!")
        );

        Product product = productRepository.findByUuid(importsProductsRequest.productUuid()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!")
        );

        ImportsProducts importsProducts = importsProductsMapper.fromImportsProductsRequest(importsProductsRequest);
        importsProducts.setUuid(RandomUtil.random6Digits());
        importsProducts.setImports(imports);
        importsProducts.setProduct(product);
        importsProducts.setAmount(importsProductsRequest.unitPrice().multiply(BigDecimal.valueOf(importsProductsRequest.importQuantity())));

        importsProductsRepository.save(importsProducts);
        return importsProductsMapper.toImportsProductsResponse(importsProducts);
    }

    @Override
    public ImportsProductsResponse updateByUuid(String uuid, UpdateImportsProductsRequest updateImportsProductsRequest) {

        if (importsProductsRepository.existsByUuid(uuid)){
            ImportsProducts importsProducts = importsProductsRepository.findByUuid(uuid).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Imports Products not found!")
            );
            importsProductsMapper.fromUpdateImportsProductsRequest(importsProducts, updateImportsProductsRequest);

            if (updateImportsProductsRequest.importsUuid() != null){
                Import imports = importRepository.findByUuid(updateImportsProductsRequest.importsUuid()).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Import not found!")
                );
                importsProducts.setImports(imports);
            }
            if (updateImportsProductsRequest.productUuid() != null){
                Product product = productRepository.findByUuid(updateImportsProductsRequest.productUuid()).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!")
                );
                importsProducts.setProduct(product);
            }
            importsProducts.setAmount(updateImportsProductsRequest.unitPrice().multiply(BigDecimal.valueOf(updateImportsProductsRequest.importQuantity())));
            importsProductsRepository.save(importsProducts);
            return importsProductsMapper.toImportsProductsResponse(importsProducts);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Imports Products not found!");
    }

    @Override
    public ImportsProductsResponse findByUuid(String uuid) {
        ImportsProducts importsProducts = importsProductsRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Imports Products not found!")
        );
        return importsProductsMapper.toImportsProductsResponse(importsProducts);
    }

    @Override
    public List<ImportsProductsResponse> findAll() {
        List<ImportsProducts> importsProductsList = importsProductsRepository.findAll();
        return importsProductsMapper.toImportsProductsResponseList(importsProductsList);
    }

    @Override
    public List<ImportsProductsResponse> findAllByImportUuid(String importUuid) {
        List<ImportsProducts> importsProductsList = importsProductsRepository.findAllByImportUuid(importUuid);
        return importsProductsMapper.toImportsProductsResponseList(importsProductsList);
    }
}
