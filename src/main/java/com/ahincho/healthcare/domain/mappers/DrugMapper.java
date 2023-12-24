package com.ahincho.healthcare.domain.mappers;

import com.ahincho.healthcare.domain.dtos.DrugRequest;
import com.ahincho.healthcare.domain.dtos.DrugResponse;
import com.ahincho.healthcare.domain.entities.CategoryEntity;
import com.ahincho.healthcare.domain.entities.DrugEntity;

public class DrugMapper {
    public static DrugResponse entityToResponse(DrugEntity drugEntity) {
        return DrugResponse.builder()
                .id(drugEntity.getId())
                .name(drugEntity.getName())
                .categoryId(drugEntity.getCategory().getId())
                .categoryName(drugEntity.getCategory().getName())
                .build();
    }
    public static DrugEntity requestToEntity(DrugRequest drugRequest) {
        return DrugEntity.builder()
                .name(drugRequest.getName())
                .category(CategoryEntity.builder().id(drugRequest.getCategoryId()).build())
                .description(drugRequest.getDescription())
                .build();
    }
}
