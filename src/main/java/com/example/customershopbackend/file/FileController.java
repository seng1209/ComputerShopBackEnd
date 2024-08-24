package com.example.customershopbackend.file;

import com.example.customershopbackend.file.feture.FileService;
import com.example.customershopbackend.file.feture.dto.FileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FileResponse uploadSingle(@RequestPart MultipartFile file) throws IOException {
        return fileService.uploadSingle(file);
    }

    @GetMapping("/{name}")
    public FileResponse findByName(@PathVariable String name) throws IOException {
        return fileService.findByName(name);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{name}")
    public void deleteByName(@PathVariable String name) throws IOException {
        fileService.deleteByName(name);
    }

}
