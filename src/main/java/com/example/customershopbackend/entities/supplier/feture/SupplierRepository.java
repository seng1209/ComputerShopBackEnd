package com.example.customershopbackend.entities.supplier.feture;

import com.example.customershopbackend.entities.supplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    Boolean existsByUuid(String uuid);

    Optional<Supplier> findByUuid(String uuid);

    @Modifying
    @Query("UPDATE Supplier AS S SET S.isDeleted = true WHERE S.uuid = ?1")
    void updateByIsDeleted(String uuid);

    List<Supplier> findAllByIsDeletedIsFalse();

}
