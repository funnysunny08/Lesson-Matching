package com.az.server.controller.response;

import java.time.LocalDateTime;

public record CreateMatchingResponseDto(String status, LocalDateTime createdAt) {
    public static CreateMatchingResponseDto of(String status, LocalDateTime createdAt) {
        return new CreateMatchingResponseDto(status, createdAt);
    }
}
