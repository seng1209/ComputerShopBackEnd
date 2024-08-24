package com.example.customershopbackend.entities.imports.feture;

import com.example.customershopbackend.entities.imports.feture.dto.ImportRequest;
import com.example.customershopbackend.entities.imports.feture.dto.ImportResponse;
import com.example.customershopbackend.entities.imports.feture.dto.UpdateImportRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/imports")
@RequiredArgsConstructor
public class ImportController {

    private final ImportService importService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ImportResponse createImport(@Valid @RequestBody ImportRequest importRequest){
        return importService.createImport(importRequest);
    }

    @PatchMapping("/{uuid}")
    public ImportResponse updateByUuid(@PathVariable String uuid, @Valid @RequestBody UpdateImportRequest updateImportRequest){
        return importService.updateByUuid(uuid, updateImportRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{uuid}")
    void updateIsDeletedByUuid(@PathVariable String uuid){
        importService.updateIsDeletedByUuid(uuid);
    }

    @GetMapping("/{uuid}")
    public ImportResponse findByUuid(@PathVariable String uuid){
        return importService.findByUuid(uuid);
    }

    @GetMapping
    public List<ImportResponse> findAll(){
        return importService.findAll();
    }

}
