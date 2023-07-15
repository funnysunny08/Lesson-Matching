package com.az.server.controller.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateLectureResponseDto(LocalDateTime createdAt) {
    public static CreateLectureResponseDto of(LocalDateTime createdAt) {
        return new CreateLectureResponseDto(createdAt);
    }
}
