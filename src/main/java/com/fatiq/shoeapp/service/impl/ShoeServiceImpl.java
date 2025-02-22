package com.fatiq.shoeapp.service.impl;

import com.fatiq.shoeapp.dto.ShoeDto;
import com.fatiq.shoeapp.entity.Brand;
import com.fatiq.shoeapp.entity.Category;
import com.fatiq.shoeapp.entity.Shoe;
import com.fatiq.shoeapp.repository.BrandRepository;
import com.fatiq.shoeapp.repository.CategoryRepository;
import com.fatiq.shoeapp.repository.ShoeRepository;
import com.fatiq.shoeapp.service.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoeServiceImpl implements ShoeService {

    @Autowired
    private ShoeRepository shoeRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ShoeDto createShoe(ShoeDto shoeDto) {
        // check if brand already exists in database if not then throw exception
        Brand brand = brandRepository.findById(shoeDto.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        // check if category already exists in database if not then throw exception
        Category category = categoryRepository.findById(shoeDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Shoe shoe = new Shoe();

        shoe.setName(shoeDto.getName());
        shoe.setDescription(shoeDto.getDescription());
        shoe.setPrice(shoeDto.getPrice());
        shoe.setSize(shoeDto.getSize());
        shoe.setStockQuantity(shoeDto.getStockQuantity());
        shoe.setBrand(brand);
        shoe.setCategory(category);
        shoe.setImageUrl(shoeDto.getImageUrl());

        Shoe savedShoe = shoeRepository.save(shoe);
        shoeDto.setShoeId(savedShoe.getShoeId());
        return shoeDto;
    }

    @Override
    public List<ShoeDto> getAllShoes() {
        return shoeRepository.findAll()
                .stream()
                .map(shoe -> {
                    ShoeDto shoeDto = new ShoeDto();
                    shoeDto.setShoeId(shoe.getShoeId());
                    shoeDto.setName(shoe.getName());
                    shoeDto.setDescription(shoe.getDescription());
                    shoeDto.setPrice(shoe.getPrice());
                    shoeDto.setSize(shoe.getSize());
                    shoeDto.setStockQuantity(shoe.getStockQuantity());
                    shoeDto.setBrandId(shoe.getBrand().getBrandId());
                    shoeDto.setCategoryId(shoe.getCategory().getCategoryId());
                    shoeDto.setImageUrl(shoe.getImageUrl());
                    return shoeDto;
                }).collect(Collectors.toList());
    }

    @Override
    public ShoeDto getShoeById(Long id) {
        Shoe shoe = shoeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shoe not found"));

        ShoeDto shoeDto = new ShoeDto();
        shoeDto.setShoeId(shoe.getShoeId());
        shoeDto.setName(shoe.getName());
        shoeDto.setDescription(shoe.getDescription());
        shoeDto.setPrice(shoe.getPrice());
        shoeDto.setSize(shoe.getSize());
        shoeDto.setStockQuantity(shoe.getStockQuantity());
        shoeDto.setBrandId(shoe.getBrand().getBrandId());
        shoeDto.setCategoryId(shoe.getCategory().getCategoryId());
        shoeDto.setImageUrl(shoe.getImageUrl());
        return shoeDto;
    }
}
