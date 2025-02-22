package com.fatiq.shoeapp.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShoeDto {

    private Long shoeId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    @NotBlank(message = "Size is mandatory")
    private String size;

    @NotNull(message = "Stock quantity is mandatory")
    private Integer stockQuantity;

    @NotNull(message = "Brand is mandatory")
    private Long brandId;

    @NotNull(message = "Category is mandatory")
    private Long categoryId;

    private String imageUrl;

}
