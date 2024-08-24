package com.example.customershopbackend.entities.payment.feture;

import com.example.customershopbackend.entities.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Boolean existsByUuid(String uuid);

    Optional<Payment> findByUuid(String uuid);

    List<Payment> findAllByIsDeletedIsFalse();

    @Modifying
    @Query("UPDATE Payment AS P SET P.isDeleted = true WHERE P.uuid = ?1")
    void updateByIsDeleted(String uuid);

}
