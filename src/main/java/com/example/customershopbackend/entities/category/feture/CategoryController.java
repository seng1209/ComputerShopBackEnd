package com.example.customershopbackend.entities.category.feture;

import com.example.customershopbackend.entities.category.feture.dto.CategoryRequest;
import com.example.customershopbackend.entities.category.feture.dto.CategoryResponse;
import com.example.customershopbackend.entities.category.feture.dto.UpdateCategoryRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        return categoryService.createCategory(categoryRequest);
    }

    @PatchMapping("/{uuid}")
    public CategoryResponse updateCategoryByUuid(@PathVariable String uuid, @Valid @RequestBody UpdateCategoryRequest updateCategoryRequest){
        return categoryService.updateCategoryByUuid(uuid, updateCategoryRequest);
    }

    @PutMapping("/{uuid}")
    public void updateIsDeletedByUuid(@PathVariable String uuid){
        categoryService.updateIsDeletedByUuid(uuid);
    }

    @GetMapping("/{uuid}")
    public CategoryResponse findByUuid(@PathVariable String uuid){
        return categoryService.findByUuid(uuid);
    }

    @GetMapping
    public List<CategoryResponse> findAll(){
        return categoryService.findAll();
    }


}
