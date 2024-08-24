package com.example.customershopbackend.file.feture;

import com.example.customershopbackend.file.feture.dto.FileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    FileResponse uploadSingle(MultipartFile file) throws IOException;

    FileResponse findByName(String name) throws IOException;

    void deleteByName(String name) throws IOException;

}
