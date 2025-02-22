package com.fatiq.shoeapp.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDto {

    private Long orderId;
    private Long userId;
    private BigDecimal totalAmount;
    private String status;
    private List<OrderDetailDTO> orderDetails;

}
