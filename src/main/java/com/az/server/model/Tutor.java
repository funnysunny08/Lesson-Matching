package com.az.server.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Tutor {
    private final UUID tutorId;
    private final String name;
    private final String university;
    private final String major;
    private final Gender gender;
    private final int age;
    private final LocalDateTime createdAt;

    @Builder
    public Tutor(UUID tutorId, String name, String university, String major, Gender gender, int age, LocalDateTime createdAt) {
        this.tutorId = tutorId;
        this.name = name;
        this.university = university;
        this.major = major;
        this.gender = gender;
        this.age = age;
        this.createdAt = createdAt;
    }
}
