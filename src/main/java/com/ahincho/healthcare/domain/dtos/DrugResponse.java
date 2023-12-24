package com.ahincho.healthcare.domain.dtos;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class DrugResponse {
    private Integer id;
    private String name;
    private Integer categoryId;
    private String categoryName;
    private String description;
}
