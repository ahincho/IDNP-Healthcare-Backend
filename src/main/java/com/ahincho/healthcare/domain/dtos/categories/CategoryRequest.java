package com.ahincho.healthcare.domain.dtos.categories;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {
    @NotBlank(message = "Name field is required")
    @Size(min = 2, max = 32, message = "Name is between 2 and 32 characters")
    private String name;
}
