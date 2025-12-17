package com.ramjee.order_service.service;

import com.ramjee.order_service.dto.request.OrderRequestDto;
import com.ramjee.order_service.dto.response.OrderResponseDto;
import com.ramjee.order_service.entity.Order;

import java.util.List;

public interface OrderMapper {

    Order toEntity(OrderRequestDto requestDto);
    OrderResponseDto toResponseDto(Order order);
    List<OrderResponseDto> toResponseDtoList(List<Order> orders);
}
