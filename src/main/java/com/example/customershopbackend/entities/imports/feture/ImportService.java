package com.example.customershopbackend.entities.imports.feture;

import com.example.customershopbackend.entities.imports.feture.dto.ImportRequest;
import com.example.customershopbackend.entities.imports.feture.dto.ImportResponse;
import com.example.customershopbackend.entities.imports.feture.dto.UpdateImportRequest;

import java.util.List;

public interface ImportService {

    ImportResponse createImport(ImportRequest importRequest);

    ImportResponse updateByUuid(String uuid, UpdateImportRequest updateImportRequest);

    void updateIsDeletedByUuid(String uuid);

    ImportResponse findByUuid(String uuid);

    List<ImportResponse> findAll();

}
