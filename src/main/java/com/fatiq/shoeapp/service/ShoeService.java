package com.fatiq.shoeapp.service;

import com.fatiq.shoeapp.dto.ShoeDto;

import java.util.List;

public interface ShoeService {

    ShoeDto createShoe(ShoeDto shoeDto);
    List<ShoeDto> getAllShoes();
    ShoeDto getShoeById(Long id);
}
