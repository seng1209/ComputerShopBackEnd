package com.example.customershopbackend.entities.imports_products.fetrue;

import com.example.customershopbackend.entities.imports_products.ImportsProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ImportsProductsRepository extends JpaRepository<ImportsProducts, Long> {

    Boolean existsByUuid(String uuid);

    Optional<ImportsProducts> findByUuid(String uuid);

    @Query("SELECT IP FROM ImportsProducts AS IP WHERE IP.imports.uuid = ?1")
    List<ImportsProducts> findAllByImportUuid(String importUuid);

    @Modifying
    @Query("UPDATE Import AS I " +
            "SET I.totalAmount = (SELECT SUM(IP.amount) FROM ImportsProducts AS IP WHERE IP.imports.uuid = ?1) " +
            "WHERE I.uuid = ?1")
    void updateTotalAmountByImportUuid(String importUuid);

}
