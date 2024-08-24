package com.example.customershopbackend.entities.sales_products.feture;

import com.example.customershopbackend.entities.sales_products.SalesProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SalesProductsRepository extends JpaRepository<SalesProducts, Long> {

    Boolean existsByUuid(String uuid);

    Optional<SalesProducts> findByUuid(String uuid);

    @Query("SELECT SP FROM SalesProducts AS SP WHERE SP.sale.uuid = ?1")
    List<SalesProducts> findAllBySaleUuid(String saleUuid);

}
