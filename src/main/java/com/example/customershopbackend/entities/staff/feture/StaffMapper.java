package com.example.customershopbackend.entities.staff.feture;

import com.example.customershopbackend.entities.staff.Staff;
import com.example.customershopbackend.entities.staff.feture.dto.StaffRequest;
import com.example.customershopbackend.entities.staff.feture.dto.StaffResponse;
import com.example.customershopbackend.entities.staff.feture.dto.UpdateStaffRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StaffMapper {

    Staff fromStaffRequest(StaffRequest staffRequest);

    void fromUpdateStaffRequest(@MappingTarget Staff staff, UpdateStaffRequest updateStaffRequest);

    StaffResponse toStaffResponse(Staff staff);

    List<StaffResponse> toStaffResponse(List<Staff> staff);

}
