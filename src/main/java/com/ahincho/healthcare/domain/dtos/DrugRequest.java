package com.ahincho.healthcare.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class DrugRequest {
    @NotBlank(message = "Name must not be blank")
    private String name;
    @NotBlank(message = "Category identifier must not be blank")
    @Positive(message = "Category identifier must be greater than 0")
    private Integer categoryId;
    @NotBlank(message = "Description must not be blank")
    @Size(min = 20, message = "Description must contain at least 20 characters")
    private String description;
}
