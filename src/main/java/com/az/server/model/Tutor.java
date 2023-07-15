package com.az.server.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tutor {
    private Long tutorId;
    private String name;
    private String university;
    private String major;
    private Gender gender;
    private int age;
    private LocalDateTime createdAt;

    public Tutor(Long tutorId, String name, String university, String major, Gender gender, int age, LocalDateTime createdAt) {
        this.tutorId = tutorId;
        this.name = name;
        this.university = university;
        this.major = major;
        this.gender = gender;
        this.age = age;
        this.createdAt = createdAt;
    }

    public Tutor(String name, String university, String major, Gender gender, int age, LocalDateTime createdAt) {
        this.name = name;
        this.university = university;
        this.major = major;
        this.gender = gender;
        this.age = age;
        this.createdAt = createdAt;
    }
}
