package com.ahincho.healthcare.domain.dtos.medicines;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineResponse {
    private Integer id;
    private String name;
    private String category;
    private String description;
    private String imageUrl;
}
