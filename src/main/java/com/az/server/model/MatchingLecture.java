package com.az.server.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MatchingLecture {
    private Long matchingLectureId;
    private MatchingStatus matchingStatus;
    private Long lectureId;
    private Long studentId;
    private LocalDateTime createdAt;

    public MatchingLecture(Long matchingLectureId, MatchingStatus matchingStatus, Long lectureId, Long studentId, LocalDateTime createdAt) {
        this.matchingLectureId = matchingLectureId;
        this.matchingStatus = matchingStatus;
        this.lectureId = lectureId;
        this.studentId = studentId;
        this.createdAt = createdAt;
    }

    public MatchingLecture(MatchingStatus matchingStatus, Long lectureId, Long studentId, LocalDateTime createdAt) {
        this.matchingStatus = matchingStatus;
        this.lectureId = lectureId;
        this.studentId = studentId;
        this.createdAt = createdAt;
    }
}
