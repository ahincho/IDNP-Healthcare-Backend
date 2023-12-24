package com.ahincho.healthcare.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrugResponse {
    private Integer id;
    private String name;
    private Integer categoryId;
    private String categoryName;
    private String description;
}
