package com.example.customershopbackend.entities.invoices_products.feture;

import com.example.customershopbackend.entities.invoices_products.InvoicesProducts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvoicesProductsRepository extends JpaRepository<InvoicesProducts, Long> {

    Boolean existsByUuid(String uuid);

    Optional<InvoicesProducts> findByUuid(String uuid);

    List<InvoicesProducts> findAllByInvoiceUuid(String uuid);

}
