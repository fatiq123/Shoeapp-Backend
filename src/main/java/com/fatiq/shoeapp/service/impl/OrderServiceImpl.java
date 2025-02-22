package com.fatiq.shoeapp.service.impl;

import com.fatiq.shoeapp.dto.OrderDetailDTO;
import com.fatiq.shoeapp.dto.OrderDto;
import com.fatiq.shoeapp.entity.Order;
import com.fatiq.shoeapp.entity.OrderDetail;
import com.fatiq.shoeapp.entity.Shoe;
import com.fatiq.shoeapp.entity.User;
import com.fatiq.shoeapp.repository.OrderRepository;
import com.fatiq.shoeapp.repository.ShoeRepository;
import com.fatiq.shoeapp.repository.UserRepository;
import com.fatiq.shoeapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShoeRepository shoeRepository;

    // Creates a new order for the authenticated user with the given order details
    @Override
    public OrderDto createOrder(List<OrderDetailDTO> orderDetailsDto) {
        // Get current authenticated username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // Find user by username or throw exception if not found
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create new order and set initial values
        Order order = new Order();
        order.setUser(user);
        order.setStatus("pending");
        BigDecimal totalAmount = BigDecimal.ZERO;

        // Process each order detail
        List<OrderDetail> orderDetails = orderDetailsDto.stream().map(dto -> {
            // Find shoe by ID or throw exception if not found
            Shoe shoe = shoeRepository.findById(dto.getShoeId())
                    .orElseThrow(() -> new RuntimeException("Shoe not found"));

            // Check if enough stock is available
            if (shoe.getStockQuantity() < dto.getQuantity()) {
                throw new RuntimeException("Insufficient stock for shoe: " + shoe.getName());
            }

            // Create order detail and set its properties
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setShoe(shoe);
            detail.setQuantity(dto.getQuantity());
            detail.setUnitPrice(shoe.getPrice());
            totalAmount.add(shoe.getPrice().multiply(BigDecimal.valueOf(dto.getQuantity())));

            // Update shoe stock quantity
            shoe.setStockQuantity(shoe.getStockQuantity() - dto.getQuantity());
            shoeRepository.save(shoe);

            return detail;
        }).collect(Collectors.toList());

        // Set final order properties and save
        order.setTotalAmount(totalAmount);
        order.setOrderDetails(orderDetails);
        Order savedOrder = orderRepository.save(order);

        // Convert saved order to DTO
        OrderDto orderDTO = new OrderDto();
        orderDTO.setOrderId(savedOrder.getOrderId());
        orderDTO.setUserId(savedOrder.getUser().getUserId());
        orderDTO.setTotalAmount(savedOrder.getTotalAmount());
        orderDTO.setStatus(savedOrder.getStatus());
        orderDTO.setOrderDetails(savedOrder.getOrderDetails().stream().map(detail -> {
            OrderDetailDTO dto = new OrderDetailDTO();
            dto.setOrderDetailId(detail.getOrderDetailId());
            dto.setShoeId(detail.getShoe().getShoeId());
            dto.setQuantity(detail.getQuantity());
            dto.setUnitPrice(detail.getUnitPrice());
            return dto;
        }).collect(Collectors.toList()));

        return orderDTO;
    }

    // Retrieves all orders for the currently authenticated user
    @Override
    public List<OrderDto> getOrdersByUser() {
        // Get current authenticated username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Find user by username or throw exception if not found
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        // Find all orders for user and convert to DTOs
        return orderRepository.findByUser(user)
                .stream()
                .map(order -> {
                    OrderDto dto = new OrderDto();
                    dto.setOrderId(order.getOrderId());
                    dto.setUserId(order.getUser().getUserId());
                    dto.setTotalAmount(order.getTotalAmount());
                    dto.setStatus(order.getStatus());
                    dto.setOrderDetails(order.getOrderDetails().stream().map(detail -> {
                        OrderDetailDTO detailDTO = new OrderDetailDTO();
                        detailDTO.setOrderDetailId(detail.getOrderDetailId());
                        detailDTO.setShoeId(detail.getShoe().getShoeId());
                        detailDTO.setQuantity(detail.getQuantity());
                        detailDTO.setUnitPrice(detail.getUnitPrice());
                        return detailDTO;
                    }).collect(Collectors.toList()));
                    return dto;
                }).collect(Collectors.toList());
    }
}