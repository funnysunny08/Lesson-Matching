package com.az.server.repository;

import com.az.server.model.MatchingLecture;
import com.az.server.model.MatchingStatus;

import java.util.List;
import java.util.Optional;

public interface MatchingLectureRepository {
    // CREATE
    MatchingLecture insert(MatchingLecture matchingLecture);

    // READ
    List<MatchingLecture> findByTutorId(Long tutorId);

    List<MatchingLecture> findByStudentId(Long studentId);

    Optional<MatchingLecture> findById(Long matchingLectureId);

    List<MatchingLecture> findAll();

    // UPDATE
    MatchingLecture updateByStatus(MatchingLecture matchingLecture);

    // DELETE

}
