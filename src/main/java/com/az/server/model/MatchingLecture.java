package com.az.server.model;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class MatchingLecture {
    private final UUID matchingLectureId;
    private final MatchingStatus matchingStatus;
    private final Lecture lecture;
    private final Student student;

    @Builder
    public MatchingLecture(UUID matchingLectureId, MatchingStatus matchingStatus, Lecture lecture, Student student) {
        this.matchingLectureId = matchingLectureId;
        this.matchingStatus = matchingStatus;
        this.lecture = lecture;
        this.student = student;
    }
}
