package com.ramjee.order_service.service.impl;

import com.ramjee.order_service.dto.request.OrderRequestDto;
import com.ramjee.order_service.dto.response.OrderResponseDto;
import com.ramjee.order_service.entity.Order;
import com.ramjee.order_service.enums.OrderStatus;
import com.ramjee.order_service.service.OrderMapper;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toEntity(OrderRequestDto requestDto) {

        if (requestDto == null) {
            return null;
        }

        return Order.builder()
                .customerId(requestDto.customerId())
                .totalAmount(requestDto.totalAmount())
                .status(OrderStatus.CREATED)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    @Override
    public OrderResponseDto toResponseDto(Order order) {

        if (order == null) {
            return null;
        }
        return new OrderResponseDto(
                order.getId(),
                order.getCustomerId(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getCreatedAt());
    }

    @Override
    public List<OrderResponseDto> toResponseDtoList(List<Order> orders) {

        if(orders == null || orders.isEmpty()){
            return List.of();
        }

        return orders.stream().map(this::toResponseDto).collect(Collectors.toList());
    }
}
