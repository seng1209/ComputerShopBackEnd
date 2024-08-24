package com.example.customershopbackend.entities.category.feture;

import com.example.customershopbackend.entities.category.Category;
import com.example.customershopbackend.entities.category.feture.dto.CategoryRequest;
import com.example.customershopbackend.entities.category.feture.dto.CategoryResponse;
import com.example.customershopbackend.entities.category.feture.dto.UpdateCategoryRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category fromCategoryRequest(CategoryRequest categoryRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateCategoryRequest(@MappingTarget Category category, UpdateCategoryRequest updateCategoryRequest);

    CategoryResponse toCategoryResponse(Category category);

    List<CategoryResponse> toCategoryResponseList(List<Category> categories);

}
