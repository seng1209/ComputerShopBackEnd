package com.example.customershopbackend.entities.invoice.feture;

import com.example.customershopbackend.entities.invoice.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Boolean existsByUuid(String uuid);

    Optional<Invoice> findByUuid(String uuid);

    List<Invoice> findAllByIsDeletedIsFalse();

    @Modifying
    @Query("UPDATE Invoice AS I SET I.isDeleted = true WHERE I.uuid = ?1")
    void updateByIsDeleted(String uuid);

}
