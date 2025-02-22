package com.fatiq.shoeapp.service.impl;

import com.fatiq.shoeapp.dto.BrandDto;
import com.fatiq.shoeapp.entity.Brand;
import com.fatiq.shoeapp.repository.BrandRepository;
import com.fatiq.shoeapp.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public BrandDto createBrand(BrandDto brandDto) {
        if (brandRepository.existsByBrandName(brandDto.getBrandName())) {
            throw new RuntimeException("Brand Already Exists");
        }

        Brand brand = new Brand();
        brand.setBrandName(brandDto.getBrandName());

        Brand savedBrand = brandRepository.save(brand);
        brandDto.setBrandId(savedBrand.getBrandId());
        return brandDto;
    }

    @Override
    public List<BrandDto> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .map(brand -> {
                    BrandDto brandDto = new BrandDto();
                    brandDto.setBrandId(brand.getBrandId());
                    brandDto.setBrandName(brand.getBrandName());
                    return brandDto;
                }).collect(Collectors.toList());
    }

    @Override
    public BrandDto getBrandById(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand Not Found"));

        BrandDto brandDto = new BrandDto();
        brandDto.setBrandId(brand.getBrandId());
        brandDto.setBrandName(brand.getBrandName());
        return brandDto;
    }
}
