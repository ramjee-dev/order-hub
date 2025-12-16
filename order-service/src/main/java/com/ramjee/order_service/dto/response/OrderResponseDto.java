package com.ramjee.order_service.dto.response;

import com.ramjee.order_service.enums.OrderStatus;

import java.time.Instant;

public record OrderResponseDto(
        Long Id,
        Long customerId,
        Double totalAmount,
        OrderStatus status,
        Instant createdAt
) {
}
