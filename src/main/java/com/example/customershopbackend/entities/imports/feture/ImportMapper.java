package com.example.customershopbackend.entities.imports.feture;

import com.example.customershopbackend.entities.imports.Import;
import com.example.customershopbackend.entities.imports.feture.dto.ImportRequest;
import com.example.customershopbackend.entities.imports.feture.dto.ImportResponse;
import com.example.customershopbackend.entities.imports.feture.dto.UpdateImportRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImportMapper {

    Import fromImportRequest(ImportRequest importRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateImportRequest(@MappingTarget Import imports, UpdateImportRequest updateImportRequest);
    @Mapping(source = "supplier", target = "supplier")
    @Mapping(source = "staff", target = "staff")
    ImportResponse toImportResponse(Import imports);

    List<ImportResponse> toImportResponseList(List<Import> imports);

}
