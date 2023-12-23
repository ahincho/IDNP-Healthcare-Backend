package com.ahincho.healthcare.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class CategoryRequest {
    @NotBlank(message = "Name must not blank")
    private String name;
}
