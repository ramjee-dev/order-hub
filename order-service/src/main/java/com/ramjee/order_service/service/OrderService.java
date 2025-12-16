package com.ramjee.order_service.service;

import com.ramjee.order_service.dto.request.OrderRequestDto;
import com.ramjee.order_service.dto.response.OrderResponseDto;

import java.util.List;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto requestDto);

    OrderResponseDto getOrderById(Long id);

    List<OrderResponseDto> getAllorders();
}
