package com.example.customershopbackend.entities.invoices_products.feture;

import com.example.customershopbackend.entities.invoice.Invoice;
import com.example.customershopbackend.entities.invoice.feture.InvoiceRepository;
import com.example.customershopbackend.entities.invoices_products.InvoicesProducts;
import com.example.customershopbackend.entities.invoices_products.feture.dto.InvoicesProductsRequest;
import com.example.customershopbackend.entities.invoices_products.feture.dto.InvoicesProductsResponse;
import com.example.customershopbackend.entities.invoices_products.feture.dto.UpdateInvoicesProducts;
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
public class InvoicesProductsServiceImpl implements InvoicesProductsService {

    private final InvoicesProductsRepository invoicesProductsRepository;
    private final InvoicesProductsMapper invoicesProductsMapper;
    private final InvoiceRepository invoiceRepository;
    private final ProductRepository productRepository;

    @Override
    public InvoicesProductsResponse createInvoicesProducts(InvoicesProductsRequest invoicesProductsRequest) {

        Invoice invoice = invoiceRepository.findByUuid(invoicesProductsRequest.invoiceUuid()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found!")
        );

        Product product = productRepository.findByUuid(invoicesProductsRequest.productUuid()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!")
        );

        InvoicesProducts invoicesProducts = invoicesProductsMapper.fromInvoiceProductsRequest(invoicesProductsRequest);
        invoicesProducts.setInvoice(invoice);
        invoicesProducts.setProduct(product);
        invoicesProducts.setUuid(RandomUtil.random6Digits());
        invoicesProducts.setAmount(invoicesProductsRequest.saleUnitPrice().multiply(BigDecimal.valueOf(invoicesProductsRequest.saleQuantity())));

        invoicesProductsRepository.save(invoicesProducts);
        return invoicesProductsMapper.toInvoicesProductsResponse(invoicesProducts);
    }

    @Override
    public InvoicesProductsResponse updateByUuid(String uuid, UpdateInvoicesProducts updateInvoicesProducts) {

        if (invoicesProductsRepository.existsByUuid(uuid)){
            InvoicesProducts invoicesProducts = invoicesProductsRepository.findByUuid(uuid).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoices Products not found!")
            );
            invoicesProductsMapper.fromUpdateInvoicesProductsRequest(invoicesProducts, updateInvoicesProducts);
            if (updateInvoicesProducts.invoiceUuid() != null){
                Invoice invoice = invoiceRepository.findByUuid(updateInvoicesProducts.invoiceUuid()).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found!")
                );
                invoicesProducts.setInvoice(invoice);
            }
            if (updateInvoicesProducts.productUuid() != null){
                Product product = productRepository.findByUuid(updateInvoicesProducts.productUuid()).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!")
                );
                invoicesProducts.setProduct(product);
            }
            invoicesProducts.setAmount(updateInvoicesProducts.saleUnitPrice().multiply(BigDecimal.valueOf(updateInvoicesProducts.saleQuantity())));
            invoicesProductsRepository.save(invoicesProducts);
            return invoicesProductsMapper.toInvoicesProductsResponse(invoicesProducts);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!");
    }

    @Override
    public InvoicesProductsResponse findByUuid(String uuid) {
        InvoicesProducts invoicesProducts = invoicesProductsRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoices Products not found!")
        );
        return invoicesProductsMapper.toInvoicesProductsResponse(invoicesProducts);
    }

    @Override
    public List<InvoicesProductsResponse> findAll() {
        List<InvoicesProducts> invoicesProductsList = invoicesProductsRepository.findAll();
        return invoicesProductsMapper.toInvoicesProductsResponseList(invoicesProductsList);
    }

    @Override
    public List<InvoicesProductsResponse> findAllByInvoiceUuid(String invoiceUuid) {
        List<InvoicesProducts> invoicesProductsResponseList = invoicesProductsRepository.findAllByInvoiceUuid(invoiceUuid);
        return invoicesProductsMapper.toInvoicesProductsResponseList(invoicesProductsResponseList);
    }
}
