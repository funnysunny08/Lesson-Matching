package com.az.server.model;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Lecture {
    private final UUID lectureId;
    private final String subject;
    private final String region;
    private final int price;
    private final int numberOfWeek;
    private final Tutor tutor;

    @Builder
    public Lecture(UUID lectureId, String subject, String region, int price, int numberOfWeek, Tutor tutor) {
        this.lectureId = lectureId;
        this.subject = subject;
        this.region = region;
        this.price = price;
        this.numberOfWeek = numberOfWeek;
        this.tutor = tutor;
    }
}
