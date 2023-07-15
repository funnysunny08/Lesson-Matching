package com.az.server.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {
    private Long studentId;
    private String name;
    private Gender gender;
    private int age;
    private String residentialArea;
    private LocalDateTime createdAt;

    public Student(Long studentId, String name, Gender gender, int age, String residentialArea, LocalDateTime createdAt) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.residentialArea = residentialArea;
        this.createdAt = createdAt;
    }

    public Student(String name, Gender gender, int age, String residentialArea, LocalDateTime createdAt) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.residentialArea = residentialArea;
        this.createdAt = createdAt;
    }
}
