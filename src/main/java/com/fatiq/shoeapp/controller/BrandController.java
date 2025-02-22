package com.fatiq.shoeapp.controller;

import com.fatiq.shoeapp.dto.ApiResponse;
import com.fatiq.shoeapp.dto.BrandDto;
import com.fatiq.shoeapp.service.BrandService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@Validated
public class BrandController {

    private static final Logger logger = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    private BrandService brandService;

    // Added @PreAuthorize("hasRole('ADMIN')") for the createBrand method,
    // ensuring only admins can create brands.
    // SecurityConfig already restricts /api/brands/** to ROLE_ADMIN,
    // but @PreAuthorize adds method-level security.
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") // Fine-grained control
    public ResponseEntity<ApiResponse<BrandDto>> createBrand(@Valid @RequestBody BrandDto brandDto) {
        logger.info("Creating brand: {}", brandDto.getBrandName());
        BrandDto createdBrand = brandService.createBrand(brandDto);
        return ResponseEntity.ok(ApiResponse.success(createdBrand, "Brand created successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BrandDto>>> getAllBrands() {
        logger.info("Fetching all brands");
        List<BrandDto> brands = brandService.getAllBrands();
        return ResponseEntity.ok(ApiResponse.success(brands, "Brands retrieved successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BrandDto>> getBrandById(@PathVariable Long id) {
        logger.info("Fetching brand with ID: {}", id);
        BrandDto brand = brandService.getBrandById(id);
        return ResponseEntity.ok(ApiResponse.success(brand, "Brand retrieved successfully"));
    }
}
