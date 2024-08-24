package com.example.customershopbackend.entities.staff.feture;

import com.example.customershopbackend.entities.staff.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    Boolean existsByUuid(String uuid);

    Optional<Staff> findByUuid(String uuid);

    Boolean existsByEmail(String email);

    Optional<Staff> findByEmail(String email);

    Boolean existsByPhone(String phone);

    Optional<Staff> findByPhone(String phone);

    List<Staff> findByIsDeletedIsFalse();

    @Modifying
    @Query("UPDATE Staff AS S SET S.isDeleted = true WHERE S.uuid = ?1")
    void updateByUuid(String uuid);

}
