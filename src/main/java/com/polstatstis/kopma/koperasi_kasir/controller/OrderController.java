package com.polstatstis.kopma.koperasi_kasir.controller;

import com.polstatstis.kopma.koperasi_kasir.dto.OrderDto;
import com.polstatstis.kopma.koperasi_kasir.dto.OrderItemDto;
import com.polstatstis.kopma.koperasi_kasir.entity.Order;
import com.polstatstis.kopma.koperasi_kasir.entity.OrderItem;
import com.polstatstis.kopma.koperasi_kasir.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders().stream()
                .map(order -> new OrderDto(
                        order.getTotalPrice(),
                        order.getPayment(),
                        order.getChangeAmount(),
                        order.getItems().stream()
                                .map(this::convertToOrderItemDto)
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            return new OrderDto(
                    order.getTotalPrice(),
                    order.getPayment(),
                    order.getChangeAmount(),
                    order.getItems().stream()
                            .map(this::convertToOrderItemDto)
                            .collect(Collectors.toList())
            );
        } else {
            // Handle order not found case if needed
            return null;
        }
    }

    @PostMapping
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        Order savedOrder = orderService.saveOrder(orderDto);
        return new OrderDto(
                savedOrder.getTotalPrice(),
                savedOrder.getPayment(),
                savedOrder.getChangeAmount(),
                orderDto.getItems()
        );
    }

    // Helper method to convert OrderItem to OrderItemDto
    private OrderItemDto convertToOrderItemDto(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getProduct().getName(),
                orderItem.getQuantity()
                );
    }
}
