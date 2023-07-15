package com.az.server.controller.request;

public record CreateLectureRequestDto(String subject, String region, int price, int numberOfWeek) {
}
