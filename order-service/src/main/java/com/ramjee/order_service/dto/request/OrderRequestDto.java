package com.ramjee.order_service.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderRequestDto(
        @NotNull Long customerId,
        @NotNull@Min(1) Double totalAmount
) {
}
