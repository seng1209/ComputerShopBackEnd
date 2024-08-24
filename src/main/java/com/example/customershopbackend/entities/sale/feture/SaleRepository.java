package com.example.customershopbackend.entities.sale.feture;

import com.example.customershopbackend.entities.sale.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    Boolean existsByUuid(String uuid);

    Optional<Sale> findByUuid(String uuid);

    List<Sale> findAllByIsDeletedIsFalse();

    @Modifying
    @Query("UPDATE Sale AS S SET S.isDeleted = true WHERE S.uuid = ?1")
    void updateByIsDeleted(String uuid);

}
