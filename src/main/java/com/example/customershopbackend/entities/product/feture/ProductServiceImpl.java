package com.example.customershopbackend.entities.product.feture;

import com.example.customershopbackend.entities.category.Category;
import com.example.customershopbackend.entities.category.feture.CategoryRepository;
import com.example.customershopbackend.entities.product.Product;
import com.example.customershopbackend.entities.product.feture.dto.ProductRequest;
import com.example.customershopbackend.entities.product.feture.dto.ProductResponse;
import com.example.customershopbackend.entities.product.feture.dto.UpdateProductRequest;
import com.example.customershopbackend.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {

        if (productRepository.existsByName(productRequest.name())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Product already exists.");
        }

        Category category = categoryRepository.findByUuid(productRequest.categoryUuid()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!")
        );

        Product product = productMapper.fromProductRequest(productRequest);
        product.setCategory(category);
        product.setUuid(RandomUtil.random6Digits());
        product.setIsDeleted(false);

        productRepository.save(product);

        return productMapper.toProductResponse(product);
    }

    @Override
    public ProductResponse updateByUuid(String uuid, UpdateProductRequest updateProductRequest) {

        if (productRepository.existsByUuid(uuid)){
            Product product = productRepository.findByUuid(uuid).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!")
            );

            productMapper.fromUpdateProductRequest(product, updateProductRequest);

            if (updateProductRequest.categoryUuid() != null){
                Category newCategory = categoryRepository.findByUuid(updateProductRequest.categoryUuid()).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!")
                );
                product.setCategory(newCategory);
            }

            productRepository.save(product);
            return productMapper.toProductResponse(product);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!");
    }

    @Transactional
    @Override
    public void updateIsDeletedByUuid(String uuid) {
        productRepository.updateByIsDeleted(uuid);
    }

    @Override
    public ProductResponse findByUuid(String uuid) {
        Product product = productRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!")
        );
        return productMapper.toProductResponse(product);
    }

    @Override
    public List<ProductResponse> findAll() {
        List<Product> products = productRepository.findAllByIsDeletedIsFalse();
        return productMapper.toProductResponseList(products);
    }
}
