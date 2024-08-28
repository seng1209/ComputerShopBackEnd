package com.example.customershopbackend.entities.sales_products.feture;

import com.example.customershopbackend.entities.sales_products.SalesProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface SalesProductsRepository extends JpaRepository<SalesProducts, Long> {

    Boolean existsByUuid(String uuid);

    Optional<SalesProducts> findByUuid(String uuid);

    @Query("SELECT SP FROM SalesProducts AS SP WHERE SP.sale.uuid = ?1")
    List<SalesProducts> findAllBySaleUuid(String saleUuid);

    @Modifying
    @Query( "UPDATE Sale AS S SET S.totalAmount = " +
            "(SELECT SUM(SP.amount) FROM SalesProducts AS SP WHERE SP.sale.uuid = ?1) " +
            "WHERE S.uuid = ?1" )
    void updateTotalAmountBySaleUuid(String saleUuid);

}
