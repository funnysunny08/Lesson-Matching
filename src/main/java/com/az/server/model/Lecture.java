package com.az.server.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecture {
    private Long lectureId;
    private String subject;
    private String region;
    private int price;
    private int numberOfWeek;
    private Long tutorId;
    private LocalDateTime createdAt;

    public Lecture(String subject, String region, int price, int numberOfWeek, Long tutorId, LocalDateTime createdAt) {
        this.subject = subject;
        this.region = region;
        this.price = price;
        this.numberOfWeek = numberOfWeek;
        this.tutorId = tutorId;
        this.createdAt = createdAt;
    }

    public Lecture(Long lectureId, String subject, String region, int price, int numberOfWeek, Long tutorId, LocalDateTime createdAt) {
        this.lectureId = lectureId;
        this.subject = subject;
        this.region = region;
        this.price = price;
        this.numberOfWeek = numberOfWeek;
        this.tutorId = tutorId;
        this.createdAt = createdAt;
    }
}
