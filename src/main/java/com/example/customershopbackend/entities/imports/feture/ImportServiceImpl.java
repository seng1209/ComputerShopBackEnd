package com.example.customershopbackend.entities.imports.feture;

import com.example.customershopbackend.entities.imports.Import;
import com.example.customershopbackend.entities.imports.feture.dto.ImportRequest;
import com.example.customershopbackend.entities.imports.feture.dto.ImportResponse;
import com.example.customershopbackend.entities.imports.feture.dto.UpdateImportRequest;
import com.example.customershopbackend.entities.staff.Staff;
import com.example.customershopbackend.entities.staff.feture.StaffRepository;
import com.example.customershopbackend.entities.supplier.Supplier;
import com.example.customershopbackend.entities.supplier.feture.SupplierRepository;
import com.example.customershopbackend.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportServiceImpl implements ImportService{

    private final ImportMapper importMapper;
    private final ImportRepository importRepository;
    private final SupplierRepository supplierRepository;
    private final StaffRepository staffRepository;

    @Override
    public ImportResponse createImport(ImportRequest importRequest) {

        Supplier supplier = supplierRepository.findByUuid(importRequest.supplierUuid()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found.")
        );

        Staff staff = staffRepository.findByUuid(importRequest.staffUuid()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found.")
        );

        Import imports = importMapper.fromImportRequest(importRequest);
        imports.setStaff(staff);
        imports.setSupplier(supplier);
        imports.setUuid(RandomUtil.random6Digits());
        imports.setImportDate(LocalDateTime.now());
        imports.setIsDeleted(false);

        importRepository.save(imports);

        return importMapper.toImportResponse(imports);
    }

    @Override
    public ImportResponse updateByUuid(String uuid, UpdateImportRequest updateImportRequest) {

        if (importRepository.existsByUuid(uuid)){
            Import imports = importRepository.findByUuid(uuid).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Import not found!")
            );
            importMapper.fromUpdateImportRequest(imports, updateImportRequest);
            if (updateImportRequest.staffUuid() != null){
                Staff staff = staffRepository.findByUuid(updateImportRequest.staffUuid()).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found.")
                );
                imports.setStaff(staff);
            }
            if (updateImportRequest.supplierUuid() != null){
                Supplier supplier = supplierRepository.findByUuid(updateImportRequest.supplierUuid()).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found.")
                );
                imports.setSupplier(supplier);
            }
            importRepository.save(imports);
            return importMapper.toImportResponse(imports);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Import not found!");
    }

    @Transactional
    @Override
    public void updateIsDeletedByUuid(String uuid) {
        importRepository.updateByIsDeleted(uuid);
    }

    @Override
    public ImportResponse findByUuid(String uuid) {
        Import imports = importRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Import not found!")
        );
        return importMapper.toImportResponse(imports);
    }

    @Override
    public List<ImportResponse> findAll() {
        List<Import> imports = importRepository.findAllByIsDeletedIsFalse();
        return importMapper.toImportResponseList(imports);
    }
}
