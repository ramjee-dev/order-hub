package com.ramjee.order_service.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponseDto {

    private String errorCode;
    private String message;
    private int status;
    private LocalDateTime timestamp;
    private String path;
}
