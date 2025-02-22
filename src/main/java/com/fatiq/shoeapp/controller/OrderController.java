package com.fatiq.shoeapp.controller;

import com.fatiq.shoeapp.dto.OrderDetailDTO;
import com.fatiq.shoeapp.dto.OrderDto;
import com.fatiq.shoeapp.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody List<OrderDetailDTO> orderDetailDTO) {
        System.out.println("Received Order Details: " + orderDetailDTO);
        OrderDto order = orderService.createOrder(orderDetailDTO);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> ordersByUser = orderService.getOrdersByUser();
        return new ResponseEntity<>(ordersByUser, HttpStatus.OK);
    }

}
