package com.example.customershopbackend.entities.staff.feture;

import com.example.customershopbackend.entities.staff.feture.dto.StaffRequest;
import com.example.customershopbackend.entities.staff.feture.dto.StaffResponse;
import com.example.customershopbackend.entities.staff.feture.dto.UpdateStaffRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staffs")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public StaffResponse createStaff(@Valid @RequestBody StaffRequest staffRequest){
        return staffService.createStaff(staffRequest);
    }

    @PatchMapping("/{uuid}")
    public StaffResponse updateByUuid(@PathVariable String uuid, @Valid @RequestBody UpdateStaffRequest updateStaffRequest){
        return staffService.updateByUuid(uuid, updateStaffRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{uuid}")
    public void updateIsDeletedByUuid(@PathVariable String uuid){
        staffService.updateIsDeletedByUuid(uuid);
    }

    @GetMapping("/phone/{phone}")
    public StaffResponse findByPhone(@PathVariable String phone){
        return staffService.findByPhone(phone);
    }

    @GetMapping("/{uuid}")
    public StaffResponse findByUuid(@PathVariable String uuid){
        return staffService.findByUuid(uuid);
    }

    @GetMapping
    public List<StaffResponse> findAll(){
        return staffService.findAll();
    }

}
