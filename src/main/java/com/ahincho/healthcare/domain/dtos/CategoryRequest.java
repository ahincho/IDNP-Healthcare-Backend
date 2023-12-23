package com.ahincho.healthcare.domain.dtos;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class CategoryRequest {
    private Integer id;
    private String name;
}