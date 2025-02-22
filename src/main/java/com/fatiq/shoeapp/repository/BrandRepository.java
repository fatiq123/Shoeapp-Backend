package com.fatiq.shoeapp.repository;

import com.fatiq.shoeapp.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    boolean existsByBrandName(String brandName);

}
