package com.fatiq.shoeapp.service;

import com.fatiq.shoeapp.dto.OrderDetailDTO;
import com.fatiq.shoeapp.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(List<OrderDetailDTO> orderDetails);
    List<OrderDto> getOrdersByUser();

}
