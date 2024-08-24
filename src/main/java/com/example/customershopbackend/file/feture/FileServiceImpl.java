package com.example.customershopbackend.file.feture;

import com.example.customershopbackend.file.feture.dto.FileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{

    @Value("${file.server-path}")
    private String server;
    @Value("${file.base-path}")
    private String basePath;


    String getExtension (MultipartFile file){
        return file.getContentType().split("/")[1];
    }

    @Override
    public FileResponse uploadSingle(MultipartFile file) {

        // 1. get extension
        String extension = this.getExtension(file);
        // 2. new name
        String name = UUID.randomUUID() + "." + extension;
        // 3. uri
        String uri = basePath + name;

        Path path = Paths.get(server + name);

        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return FileResponse.builder()
                .name(name)
                .uri(uri)
                .extension(extension)
                .size(file.getSize())
                .build();
    }

    @Override
    public FileResponse findByName(String name) throws IOException {

        Path path = Paths.get(server + name);
        String extension = name.split(".")[1];

        if (Files.exists(path)){
            Resource resource = UrlResource.from(path.toUri());
            return FileResponse.builder()
                    .name(name)
                    .uri(basePath + name)
                    .extension(extension)
                    .size(resource.contentLength())
                    .build();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found!");
    }

    @Override
    public void deleteByName(String name) {
        Path path = Paths.get(server + name);

        if (Files.exists(path)){
            try {
                Files.delete(path);
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This file not found");
    }
}
