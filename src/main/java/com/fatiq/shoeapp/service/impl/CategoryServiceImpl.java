package com.fatiq.shoeapp.service.impl;

import com.fatiq.shoeapp.dto.CategoryDto;
import com.fatiq.shoeapp.entity.Category;
import com.fatiq.shoeapp.repository.CategoryRepository;
import com.fatiq.shoeapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        // if category already exists then throw exception
        if (categoryRepository.existsByCategoryName(categoryDto.getCategoryName())) {
            throw new RuntimeException("Category already exists");
        }

        Category category = new Category();

        category.setCategoryName(categoryDto.getCategoryName());
        Category savedCategory = categoryRepository.save(category);

        category.setCategoryId(savedCategory.getCategoryId());
        return categoryDto;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> {
                    CategoryDto categoryDto = new CategoryDto();
                    categoryDto.setCategoryId(categoryDto.getCategoryId());
                    categoryDto.setCategoryName(category.getCategoryName());
                    return categoryDto;
                }).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryName(category.getCategoryName());
        return categoryDto;
    }
}
