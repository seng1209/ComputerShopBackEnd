package com.example.customershopbackend.entities.staff.feture;

import com.example.customershopbackend.entities.staff.Staff;
import com.example.customershopbackend.entities.staff.feture.dto.StaffRequest;
import com.example.customershopbackend.entities.staff.feture.dto.StaffResponse;
import com.example.customershopbackend.entities.staff.feture.dto.UpdateStaffRequest;
import com.example.customershopbackend.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService{

    private final StaffMapper staffMapper;
    private final StaffRepository staffRepository;

    @Override
    public StaffResponse createStaff(StaffRequest staffRequest) {

        if (staffRepository.existsByPhone(staffRequest.phone())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already used!");
        }

        if (staffRepository.existsByEmail(staffRequest.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already used!");
        }

        Staff staff = staffMapper.fromStaffRequest(staffRequest);
        staff.setUuid(RandomUtil.random6Digits());
        staff.setHiredDate(LocalDate.now());
        staff.setIsDeleted(false);

        staffRepository.save(staff);

        return staffMapper.toStaffResponse(staff);
    }

    @Override
    public StaffResponse updateByUuid(String uuid, UpdateStaffRequest updateStaffRequest) {

        if (staffRepository.existsByUuid(uuid)){
            Staff staff = staffRepository.findByUuid(uuid).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found!")
            );
            staffMapper.fromUpdateStaffRequest(staff, updateStaffRequest);
            staffRepository.save(staff);
            return staffMapper.toStaffResponse(staff);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found!");
    }

    @Transactional
    @Override
    public void updateIsDeletedByUuid(String uuid) {
        staffRepository.updateByUuid(uuid);
    }

    @Override
    public StaffResponse findByPhone(String phone) {
        Staff staff = staffRepository.findByPhone(phone).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found!")
        );
        return staffMapper.toStaffResponse(staff);
    }

    @Override
    public StaffResponse findByUuid(String uuid) {
        Staff staff = staffRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found!")
        );
        return staffMapper.toStaffResponse(staff);
    }

    @Override
    public List<StaffResponse> findAll() {
        List<Staff> staffs = staffRepository.findByIsDeletedIsFalse();
        return staffMapper.toStaffResponse(staffs);
    }
}
