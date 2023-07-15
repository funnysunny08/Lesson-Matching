package com.az.server.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Lecture {
    private final UUID lectureId;
    private final String subject;
    private final String region;
    private final int price;
    private final int numberOfWeek;
    private final UUID tutorId;
    private final LocalDateTime createdAt;

    @Builder
    public Lecture(UUID lectureId, String subject, String region, int price, int numberOfWeek, UUID tutorId, LocalDateTime createdAt) {
        this.lectureId = lectureId;
        this.subject = subject;
        this.region = region;
        this.price = price;
        this.numberOfWeek = numberOfWeek;
        this.tutorId = tutorId;
        this.createdAt = createdAt;
    }
}
