package com.example.customershopbackend.entities.category.feture;

import com.example.customershopbackend.entities.category.feture.dto.CategoryRequest;
import com.example.customershopbackend.entities.category.feture.dto.CategoryResponse;
import com.example.customershopbackend.entities.category.feture.dto.UpdateCategoryRequest;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(CategoryRequest categoryRequest);

    CategoryResponse updateCategoryByUuid(String uuid, UpdateCategoryRequest updateCategoryRequest);

    void updateIsDeletedByUuid(String uuid);

    CategoryResponse findByUuid(String uuid);

    List<CategoryResponse> findAll();

}
