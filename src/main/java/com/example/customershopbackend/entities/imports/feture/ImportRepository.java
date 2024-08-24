package com.example.customershopbackend.entities.imports.feture;

import com.example.customershopbackend.entities.imports.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ImportRepository extends JpaRepository<Import, Long> {

    Boolean existsByUuid(String uuid);

    Optional<Import> findByUuid(String uuid);

    List<Import> findAllByIsDeletedIsFalse();

    @Modifying
    @Query("UPDATE Import AS I SET I.isDeleted = true WHERE I.uuid = ?1")
    void updateByIsDeleted(String uuid);

}
