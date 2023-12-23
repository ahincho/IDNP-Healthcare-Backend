package com.ahincho.healthcare.domain.mappers;

import com.ahincho.healthcare.domain.dtos.DrugCategoryRequest;
import com.ahincho.healthcare.domain.dtos.DrugCategoryResponse;
import com.ahincho.healthcare.domain.entities.DrugCategoryEntity;

public class DrugCategoryMapper {
    public static DrugCategoryResponse entityToResponse(DrugCategoryEntity drugCategoryEntity) {
        return DrugCategoryResponse.builder()
                .id(drugCategoryEntity.getId())
                .name(drugCategoryEntity.getName())
                .build();
    }
    public static DrugCategoryEntity requestToEntity(DrugCategoryRequest drugCategoryRequest) {
        return DrugCategoryEntity.builder()
                .id(drugCategoryRequest.getId())
                .name(drugCategoryRequest.getName())
                .build();
    }
}
