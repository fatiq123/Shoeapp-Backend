package com.fatiq.shoeapp.repository;

import com.fatiq.shoeapp.entity.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoeRepository extends JpaRepository<Shoe, Long> {

}
