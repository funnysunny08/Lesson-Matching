package com.az.server.repository;

import com.az.server.model.MatchingLecture;
import com.az.server.model.MatchingStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MatchingLectureRepository {
    // CREATE
    MatchingLecture insert(MatchingLecture matchingLecture);

    // READ
    List<MatchingLecture> findByTutorId(UUID tutorId);

    List<MatchingLecture> findByStudentId(UUID studentId);

    Optional<MatchingLecture> findById(UUID matchingLectureId);

    List<MatchingLecture> findAll();

    // UPDATE
    MatchingLecture updateByStatus(MatchingLecture matchingLecture);

    // DELETE

}
