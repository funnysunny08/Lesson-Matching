package com.az.server.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Student {
    private final UUID studentId;
    private final String name;
    private final Gender gender;
    private final int age;
    private final String residentialArea;
    private final LocalDateTime createdAt;

    @Builder
    public Student(UUID studentId, String name, Gender gender, int age, String residentialArea, LocalDateTime createdAt) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.residentialArea = residentialArea;
        this.createdAt = createdAt;
    }
}
