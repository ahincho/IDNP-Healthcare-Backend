package com.ahincho.healthcare.application.mappers;

import com.ahincho.healthcare.domain.dtos.categories.CategoryRequest;
import com.ahincho.healthcare.domain.dtos.categories.CategoryResponse;
import com.ahincho.healthcare.domain.entities.CategoryEntity;

public class CategoryMapper {
    public static CategoryResponse entityToResponse(CategoryEntity categoryEntity) {
        return CategoryResponse.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .build();
    }
    public static CategoryEntity requestToEntity(CategoryRequest categoryRequest) {
        return CategoryEntity.builder()
                .name(categoryRequest.getName())
                .build();
    }
}
