package com.example.customershopbackend.file.feture.dto;

import lombok.Builder;

@Builder
public record FileResponse(
        String name,
        String uri,
        String extension,
        Long size
) {
}
