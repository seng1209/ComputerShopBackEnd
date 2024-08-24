package com.example.customershopbackend.entities.customer_type.feture;

import com.example.customershopbackend.entities.customer_type.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerTypeRepository extends JpaRepository<CustomerType, Integer> {

    Boolean existsByType(String type);

    Optional<CustomerType> findByType(String type);

    Boolean existsByUuid(String uuid);

    Optional<CustomerType> findByUuid(String uuid);

    @Query("SELECT T FROM CustomerType AS T WHERE T.type = 'NORMAL'")
    CustomerType findByNormal();

    @Query("SELECT T FROM CustomerType AS T WHERE T.type = 'BRONZE'")
    CustomerType findByBronze();

    @Query("SELECT T FROM CustomerType AS T WHERE T.type = 'SILVER'")
    CustomerType findBySilver();

    @Query("SELECT T FROM CustomerType AS T WHERE T.type = 'GOLD'")
    CustomerType findByGold();

}
