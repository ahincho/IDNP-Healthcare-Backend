package com.ahincho.healthcare.domain.dtos.medicines;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineRequest {
    @NotBlank(message = "Name field is required")
    private String name;
    @NotNull(message = "Category identifier is required")
    @Positive(message = "Category identifier must be greater than 0")
    private Integer categoryId;
    @NotBlank(message = "Description is required")
    @Size(min = 20, max = 256, message = "Description is between 20 and 256 characters")
    private String description;
    @NotBlank(message = "Image link is required")
    private String imageUrl;
}
