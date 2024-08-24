package com.example.customershopbackend.entities.category.feture;

import com.example.customershopbackend.entities.category.Category;
import com.example.customershopbackend.entities.category.feture.dto.CategoryRequest;
import com.example.customershopbackend.entities.category.feture.dto.CategoryResponse;
import com.example.customershopbackend.entities.category.feture.dto.UpdateCategoryRequest;
import com.example.customershopbackend.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {

        Category category = categoryMapper.fromCategoryRequest(categoryRequest);
        category.setUuid(RandomUtil.random6Digits());
        category.setIsDeleted(false);
        categoryRepository.save(category);

        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public CategoryResponse updateCategoryByUuid(String uuid, UpdateCategoryRequest updateCategoryRequest) {

        if (categoryRepository.existsByUuid(uuid)){
            Category category = categoryRepository.findByUuid(uuid).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!")
            );
            categoryMapper.fromUpdateCategoryRequest(category, updateCategoryRequest);
            categoryRepository.save(category);
            return categoryMapper.toCategoryResponse(category);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!");
    }

    @Transactional
    @Override
    public void updateIsDeletedByUuid(String uuid) {
        categoryRepository.updateByUuid(uuid);
    }

    @Override
    public CategoryResponse findByUuid(String uuid) {

        Category category = categoryRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!")
        );

        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public List<CategoryResponse> findAll() {
        List<Category> categories = categoryRepository.findAllByIsDeletedIsFalse();
        return categoryMapper.toCategoryResponseList(categories);
    }
}
