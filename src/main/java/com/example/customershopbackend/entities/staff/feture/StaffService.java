package com.example.customershopbackend.entities.staff.feture;

import com.example.customershopbackend.entities.staff.feture.dto.StaffRequest;
import com.example.customershopbackend.entities.staff.feture.dto.StaffResponse;
import com.example.customershopbackend.entities.staff.feture.dto.UpdateStaffRequest;

import java.util.List;

public interface StaffService {

    StaffResponse createStaff(StaffRequest staffRequest);

    StaffResponse updateByUuid(String uuid, UpdateStaffRequest updateStaffRequest);

    void updateIsDeletedByUuid(String uuid);

    StaffResponse findByPhone(String phone);

    StaffResponse findByUuid(String uuid);

    List<StaffResponse> findAll();

}
