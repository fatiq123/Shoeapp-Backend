package com.fatiq.shoeapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BrandDto {

    private Long brandId;

    @NotBlank(message = "Brand name is mandatory")
    private String brandName;

}
