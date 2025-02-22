package com.fatiq.shoeapp.service;

import com.fatiq.shoeapp.dto.BrandDto;

import java.util.List;

public interface BrandService {

    BrandDto createBrand(BrandDto brandDto);
    List<BrandDto> getAllBrands();
    BrandDto getBrandById(Long id);

}
