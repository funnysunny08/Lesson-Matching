package com.az.server.controller.response;

public record LectureResponseDto(Long lectureId, String subject, String region, int price, int numberOfWeek,
                                 String tutorName, String tutorUniversity, String tutorMajor, String tutorGender,
                                 int tutorAge) {
    public static LectureResponseDto of(Long lectureId, String subject, String region, int price, int numberOfWeek, String tutorName, String tutorUniversity, String tutorMajor, String tutorGender, int tutorAge) {
        return new LectureResponseDto(lectureId, subject, region, price, numberOfWeek, tutorName, tutorUniversity, tutorMajor, tutorGender, tutorAge);
    }
}
