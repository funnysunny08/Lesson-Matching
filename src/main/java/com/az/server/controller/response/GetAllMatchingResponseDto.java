package com.az.server.controller.response;

public record GetAllMatchingResponseDto(Long matchingLectureId, String status) {
    public static GetAllMatchingResponseDto of(Long matchingLectureId, String status) {
        return new GetAllMatchingResponseDto(matchingLectureId, status);
    }
}
