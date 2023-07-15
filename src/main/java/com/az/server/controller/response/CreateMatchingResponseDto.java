package com.az.server.controller.response;

import java.time.LocalDateTime;

public record CreateMatchingResponseDto(LocalDateTime createdAt) {
    public static CreateMatchingResponseDto of(LocalDateTime createdAt) {
        return new CreateMatchingResponseDto(createdAt);
    }
}
