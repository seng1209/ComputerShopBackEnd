package com.example.customershopbackend.entities.customer.feture;

import com.example.customershopbackend.entities.customer.Customer;
import com.example.customershopbackend.entities.customer.feture.dto.UpdateCustomerType;
import com.example.customershopbackend.entities.customer_type.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Boolean existsByPhone(String phone);

    Optional<Customer> findByPhone(String phone);

    Boolean existsByEmail(String email);

    Optional<Customer> findByEmail(String email);

    Boolean existsByUuid(String uuid);

    Optional<Customer> findByUuid(String uuid);

    @Modifying
    @Query("UPDATE Customer AS C SET C.isDeleted = true WHERE C.uuid = ?1")
    void updateByUuid(String uuid);

    @Modifying
    @Query("UPDATE Customer AS C SET C.isDeleted = true WHERE C.phone = ?1")
    void updateByPhone(String phone);

    @Modifying
    @Query("UPDATE Customer AS C SET C.isDeleted = true WHERE C.email = ?1")
    void updateByEmail(String email);

    List<Customer> findAllByIsDeletedIsFalse();

}
