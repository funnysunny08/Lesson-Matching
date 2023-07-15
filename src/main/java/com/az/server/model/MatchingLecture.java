package com.az.server.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class MatchingLecture {
    private final UUID matchingLectureId;
    private final MatchingStatus matchingStatus;
    private final UUID lectureId;
    private final UUID studentId;
    private final LocalDateTime createdAt;

    @Builder
    public MatchingLecture(UUID matchingLectureId, MatchingStatus matchingStatus, UUID lectureId, UUID studentId, LocalDateTime createdAt) {
        this.matchingLectureId = matchingLectureId;
        this.matchingStatus = matchingStatus;
        this.lectureId = lectureId;
        this.studentId = studentId;
        this.createdAt = createdAt;
    }
}
