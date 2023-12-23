package com.ahincho.healthcare.domain.mappers;

import com.ahincho.healthcare.domain.dtos.CategoryRequest;
import com.ahincho.healthcare.domain.dtos.CategoryResponse;
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
                .id(categoryRequest.getId())
                .name(categoryRequest.getName())
                .build();
    }
}
