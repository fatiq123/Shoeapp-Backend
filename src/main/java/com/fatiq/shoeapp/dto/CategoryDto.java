package com.fatiq.shoeapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDto {

    private Long categoryId;

    @NotBlank(message = "Category name is mandatory")
    private String categoryName;

}
