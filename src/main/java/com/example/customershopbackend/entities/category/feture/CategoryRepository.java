package com.example.customershopbackend.entities.category.feture;

import com.example.customershopbackend.entities.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Boolean existsByUuid(String uuid);

    Optional<Category> findByUuid(String uuid);

    @Modifying
    @Query("UPDATE Category AS C SET C.isDeleted = true WHERE C.uuid = ?1")
    void updateByUuid(String uuid);

    List<Category> findAllByIsDeletedIsFalse();

}
