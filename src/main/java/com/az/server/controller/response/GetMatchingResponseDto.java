package com.az.server.controller.response;

public record GetMatchingResponseDto(Long matchingLectureId, Long tutorId, String tutorName, Long studentId, String studentName, String subject, String region, int price, int numberOfWeek, String status) {
    public static GetMatchingResponseDto of(Long matchingLectureId, Long tutorId, String tutorName, Long studentId, String studentName, String subject, String region, int price, int numberOfWeek, String status) {
        return new GetMatchingResponseDto(matchingLectureId, tutorId, tutorName, studentId, studentName, subject, region, price, numberOfWeek, status);
    }
}
