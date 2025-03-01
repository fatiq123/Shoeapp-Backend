package com.fatiq.shoeapp.repository;

import com.fatiq.shoeapp.entity.Order;
import com.fatiq.shoeapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);

}
