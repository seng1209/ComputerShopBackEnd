package com.example.customershopbackend.entities.product.feture;

import com.example.customershopbackend.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Boolean existsByUuid(String uuid);

    Optional<Product> findByUuid(String uuid);

    Boolean existsByName(String name);

    Optional<Product> findByName(String name);

    List<Product> findAllByIsDeletedIsFalse();

    @Modifying
    @Query("UPDATE Product AS P SET P.isDeleted = true WHERE P.uuid = ?1")
    void updateByIsDeleted(String uuid);

}
