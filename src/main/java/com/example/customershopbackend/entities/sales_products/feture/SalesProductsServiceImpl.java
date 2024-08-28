package com.example.customershopbackend.entities.sales_products.feture;

import com.example.customershopbackend.entities.product.Product;
import com.example.customershopbackend.entities.product.feture.ProductRepository;
import com.example.customershopbackend.entities.sale.Sale;
import com.example.customershopbackend.entities.sale.feture.SaleRepository;
import com.example.customershopbackend.entities.sales_products.SalesProducts;
import com.example.customershopbackend.entities.sales_products.feture.dto.SalesProductsRequest;
import com.example.customershopbackend.entities.sales_products.feture.dto.SalesProductsResponse;
import com.example.customershopbackend.entities.sales_products.feture.dto.UpdateSalesProductsRequest;
import com.example.customershopbackend.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalesProductsServiceImpl implements SalesProductsService{

    private final SalesProductsRepository salesProductsRepository;
    private final SalesProductsMapper salesProductsMapper;
    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    @Override
    public SalesProductsResponse createSalesProducts(SalesProductsRequest salesProductsRequest) {

        Sale sale = saleRepository.findByUuid(salesProductsRequest.saleUuid()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale not found!")
        );

        Product product = productRepository.findByUuid(salesProductsRequest.productUuid()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!")
        );

        SalesProducts salesProducts = salesProductsMapper.fromSalesProductsRequest(salesProductsRequest);
        salesProducts.setUuid(RandomUtil.random6Digits());
        salesProducts.setProduct(product);
        salesProducts.setSale(sale);
        salesProducts.setAmount(salesProductsRequest.saleUnitPrice().multiply(BigDecimal.valueOf(salesProductsRequest.saleQuantity())).subtract(salesProductsRequest.discount()));
        log.info("Amount : {}", salesProducts.getAmount());
        salesProductsRepository.save(salesProducts);
        return salesProductsMapper.toSalesProductsResponse(salesProducts);

    }

    @Override
    public SalesProductsResponse updateByUuid(String uuid, UpdateSalesProductsRequest updateSalesProductsRequest) {

        if (salesProductsRepository.existsByUuid(uuid)){
            SalesProducts salesProducts = salesProductsRepository.findByUuid(uuid).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales Products not found!")
            );
            salesProductsMapper.fromUpdateSalesProductsRequest(salesProducts, updateSalesProductsRequest);
            if (updateSalesProductsRequest.saleUuid() != null){
                Sale sale = saleRepository.findByUuid(updateSalesProductsRequest.saleUuid()).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale not found!")
                );
                salesProducts.setSale(sale);
            }
            if (updateSalesProductsRequest.productUuid() != null){
                Product product = productRepository.findByUuid(updateSalesProductsRequest.productUuid()).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!")
                );
                salesProducts.setProduct(product);
            }
            salesProducts.setAmount(updateSalesProductsRequest.saleUnitPrice().multiply(BigDecimal.valueOf(updateSalesProductsRequest.saleQuantity())).subtract(updateSalesProductsRequest.discount()));
            salesProductsRepository.save(salesProducts);
            return salesProductsMapper.toSalesProductsResponse(salesProducts);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales Products not found!");
    }

    @Override
    public SalesProductsResponse findByUuid(String uuid) {
        SalesProducts salesProducts = salesProductsRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales Products not found!")
        );
        return salesProductsMapper.toSalesProductsResponse(salesProducts);
    }

    @Override
    public List<SalesProductsResponse> findAll() {
        List<SalesProducts> salesProductsList = salesProductsRepository.findAll();
        return salesProductsMapper.toSalesProductsResponseList(salesProductsList);
    }

    @Override
    public List<SalesProductsResponse> findAllBySaleUuid(String saleUuid) {
        List<SalesProducts> salesProductsList = salesProductsRepository.findAllBySaleUuid(saleUuid);
        return salesProductsMapper.toSalesProductsResponseList(salesProductsList);
    }

    @Transactional
    @Override
    public void updateTotalAmount(String saleUuid) {
        salesProductsRepository.updateTotalAmountBySaleUuid(saleUuid);
    }
}
