package com.az.server.controller.response;

public record UpdateMatchingStatusResponseDto(String requestName, String requestType, String status) {
    public static UpdateMatchingStatusResponseDto of(String requestName, String requestType, String status) {
        return new UpdateMatchingStatusResponseDto(requestName, requestType, status);
    }
}
