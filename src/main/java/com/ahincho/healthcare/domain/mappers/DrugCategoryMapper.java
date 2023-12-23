package com.ahincho.healthcare.domain.mappers;

import com.ahincho.healthcare.domain.dtos.DrugCategoryRequest;
import com.ahincho.healthcare.domain.dtos.DrugCategoryResponse;
import com.ahincho.healthcare.domain.entities.CategoryEntity;

public class DrugCategoryMapper {
    public static DrugCategoryResponse entityToResponse(CategoryEntity categoryEntity) {
        return DrugCategoryResponse.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .build();
    }
    public static CategoryEntity requestToEntity(DrugCategoryRequest drugCategoryRequest) {
        return CategoryEntity.builder()
                .id(drugCategoryRequest.getId())
                .name(drugCategoryRequest.getName())
                .build();
    }
}
