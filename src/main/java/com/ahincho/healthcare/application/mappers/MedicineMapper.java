package com.ahincho.healthcare.application.mappers;

import com.ahincho.healthcare.domain.dtos.medicines.MedicineRequest;
import com.ahincho.healthcare.domain.dtos.medicines.MedicineResponse;
import com.ahincho.healthcare.domain.entities.CategoryEntity;
import com.ahincho.healthcare.domain.entities.MedicineEntity;

public class MedicineMapper {
    public static MedicineResponse entityToResponse(MedicineEntity medicineEntity) {
        return MedicineResponse.builder()
                .id(medicineEntity.getId())
                .name(medicineEntity.getName())
                .category(medicineEntity.getCategory().getName())
                .description(medicineEntity.getDescription())
                .build();
    }
    public static MedicineEntity requestToEntity(MedicineRequest medicineRequest) {
        return MedicineEntity.builder()
                .name(medicineRequest.getName())
                .category(CategoryEntity.builder().id(medicineRequest.getCategoryId()).build())
                .description(medicineRequest.getDescription())
                .build();
    }
}
