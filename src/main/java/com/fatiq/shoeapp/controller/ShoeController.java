package com.fatiq.shoeapp.controller;

import com.fatiq.shoeapp.dto.ShoeDto;
import com.fatiq.shoeapp.service.ShoeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shoes")
@Validated
public class ShoeController {

    @Autowired
    private ShoeService shoeService;

    @PostMapping
    public ResponseEntity<ShoeDto> createShoe(@Valid @RequestBody ShoeDto shoeDto) {
        ShoeDto shoe = shoeService.createShoe(shoeDto);
        return new ResponseEntity<>(shoe, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ShoeDto>> getAllShoes() {
        return ResponseEntity.ok(shoeService.getAllShoes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoeDto> getShoeById(@PathVariable(name = "id") Long shoeId) {
        return ResponseEntity.ok(shoeService.getShoeById(shoeId));
    }

}
